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


public class BaseSwipeDragAdapter extends ItemTouchHelper.SimpleCallback {
    private final static int dragDirs = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
    protected GenericAdapter mAdapter;
    protected Drawable icon_left;
    protected Drawable icon_right;
    protected ColorDrawable background;
    protected IOnSwipe iOnSwipe;
    private int swiperDirs;
    private int backgroundCornerOffset = 20;

    public BaseSwipeDragAdapter(GenericAdapter adapter, IOnSwipe iOnSwipe, int swiperDirs) {
        super(dragDirs, swiperDirs);
        this.swiperDirs = swiperDirs;
        mAdapter = adapter;
        this.iOnSwipe = iOnSwipe;
    }

    public void setBackgroundCornerOffset(int backgroundCornerOffset) {
        this.backgroundCornerOffset = backgroundCornerOffset;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        iOnSwipe.OnSwipe(mAdapter.getItem(viewHolder.getAbsoluteAdapterPosition()), viewHolder.getAbsoluteAdapterPosition(), i);
        mAdapter.notifyItemChanged(viewHolder.getAbsoluteAdapterPosition());
    }

    @Override
    public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return super.getSwipeDirs(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        setDrawables();
        int iconMargin = (itemView.getHeight() - icon_left.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + (itemView.getHeight() - icon_left.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + icon_left.getIntrinsicHeight();

        if (dX > 0) { // Swiping to the right
            int iconLeft = itemView.getLeft() + iconMargin + icon_left.getIntrinsicWidth();
            int iconRight = itemView.getLeft() + iconMargin;
            icon_left.setBounds(iconRight, iconTop, iconLeft, iconBottom);

            background.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + ((int) dX) + backgroundCornerOffset,
                    itemView.getBottom());
        } else if (dX < 0) { // Swiping to the left
            int iconLeft = itemView.getRight() - iconMargin - icon_left.getIntrinsicWidth();
            int iconRight = itemView.getRight() - iconMargin;
            icon_right.setBounds(iconLeft, iconTop, iconRight, iconBottom);
            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        } else { // view is unSwiped
            background.setBounds(0, 0, 0, 0);
            icon_left.setBounds(0, 0, 0, 0);
            icon_right.setBounds(0, 0, 0, 0);

        }

        background.draw(c);
        icon_left.draw(c);
        icon_right.draw(c);
    }

    private void setDrawables() {
        if (icon_left == null) {
            icon_left = ContextCompat.getDrawable(mAdapter.getContext(), R.drawable.delete);
        }
        if (icon_right == null) {
            icon_right = ContextCompat.getDrawable(mAdapter.getContext(), R.drawable.swipecollapse);
        }
        if (background == null) {
            background = new ColorDrawable(Color.WHITE);
        }

    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(dragDirs, swiperDirs);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onRowMoved(viewHolder.getAbsoluteAdapterPosition(), target.getAbsoluteAdapterPosition());
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof BaseViewHolder) {
                BaseViewHolder myViewHolder = (BaseViewHolder) viewHolder;
                mAdapter.onRowSelected(myViewHolder);
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        if (viewHolder instanceof BaseViewHolder) {
            BaseViewHolder myViewHolder = (BaseViewHolder) viewHolder;
            mAdapter.onRowClear(myViewHolder);
        }
    }

    public void setIcon_left(Drawable icon_left) {
        this.icon_left = icon_left;
    }

    public void setIcon_right(Drawable icon_right) {
        this.icon_right = icon_right;
    }

}