package com.emrhmrc.genericrecycler.adapters;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.emrhmrc.genericrecycler.R;
import com.emrhmrc.genericrecycler.interfaces.IOnSwipe;


public class BaseAdapterSwipe extends ItemTouchHelper.SimpleCallback {
    private GenericAdapter mAdapter;
    private Drawable icon_left;
    private Drawable icon_right;
    private ColorDrawable background;
    private IOnSwipe iOnSwipe;

    public BaseAdapterSwipe(GenericAdapter adapter, IOnSwipe iOnSwipe) {
        //hangi yönlerde swipe var
        super(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT);
        mAdapter = adapter;
        icon_left = ContextCompat.getDrawable(mAdapter.getContext(),
                R.drawable.delete);
        icon_right = ContextCompat.getDrawable(mAdapter.getContext(),
                R.drawable.swipecollapse);
        background = new ColorDrawable(Color.WHITE);
        this.iOnSwipe = iOnSwipe;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        //işlem bitince ne olsun
        //mAdapter.remove(viewHolder.getAdapterPosition());
        iOnSwipe.OnSwipe(mAdapter.getItem(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition(), i);
        mAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
    }

    @Override
    public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        return super.getSwipeDirs(recyclerView, viewHolder);

    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX,
                dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;

        int iconMargin = (itemView.getHeight() - icon_left.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + (itemView.getHeight() - icon_left.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + icon_left.getIntrinsicHeight();

        if (dX > 0) { // Swiping to the right
            int iconLeft = itemView.getLeft() + iconMargin + icon_left.getIntrinsicWidth();
            int iconRight = itemView.getLeft() + iconMargin;
            icon_left.setBounds(iconRight, iconTop, iconLeft, iconBottom);

            background.setBounds(itemView.getLeft(), itemView.getTop(),
                    itemView.getLeft() + ((int) dX) + backgroundCornerOffset,
                    itemView.getBottom());
        } else if (dX < 0) { // Swiping to the left
            int iconLeft = itemView.getRight() - iconMargin - icon_left.getIntrinsicWidth();
            int iconRight = itemView.getRight() - iconMargin;
            icon_right.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
        } else { // view is unSwiped
            background.setBounds(0, 0, 0, 0);
            icon_left.setBounds(0, 0, 0, 0);
            icon_right.setBounds(0, 0, 0, 0);

        }

        background.draw(c);
        icon_left.draw(c);
        icon_right.draw(c);
    }


}