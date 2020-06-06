package com.emrhmrc.genericrecyclerview.adapter;

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
import com.emrhmrc.genericrecycler.adapters.GenericAdapter;
import com.emrhmrc.genericrecycler.interfaces.IOnSwipe;


public class TestAdapterSwipe extends ItemTouchHelper.SimpleCallback {
    private GenericAdapter mAdapter;
    private Drawable icon_left;
    private ColorDrawable background;
    private IOnSwipe iOnSwipe;

    public TestAdapterSwipe(GenericAdapter adapter, IOnSwipe iOnSwipe) {
        super(0, ItemTouchHelper.RIGHT);
        mAdapter = adapter;
        icon_left = ContextCompat.getDrawable(mAdapter.getContext(),
                R.drawable.delete);
        background = new ColorDrawable(Color.WHITE);
        this.iOnSwipe = iOnSwipe;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        iOnSwipe.OnSwipe(mAdapter.getItem(viewHolder.getAbsoluteAdapterPosition()),
                viewHolder.getAbsoluteAdapterPosition(), i);
        mAdapter.notifyItemChanged(viewHolder.getAbsoluteAdapterPosition());
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
        } else { // view is unSwiped
            background.setBounds(0, 0, 0, 0);
            icon_left.setBounds(0, 0, 0, 0);


        }

        background.draw(c);
        icon_left.draw(c);

    }


}