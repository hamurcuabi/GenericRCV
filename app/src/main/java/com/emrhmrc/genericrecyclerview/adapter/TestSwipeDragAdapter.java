package com.emrhmrc.genericrecyclerview.adapter;

import androidx.recyclerview.widget.ItemTouchHelper;

import com.emrhmrc.genericrecycler.adapters.BaseSwipeDragAdapter;
import com.emrhmrc.genericrecycler.adapters.GenericAdapter;
import com.emrhmrc.genericrecycler.interfaces.IOnSwipe;


public class TestSwipeDragAdapter extends BaseSwipeDragAdapter {
    private final static int swipeFlags = ItemTouchHelper.RIGHT;

    public TestSwipeDragAdapter(GenericAdapter adapter, IOnSwipe iOnSwipe) {
        super(adapter, iOnSwipe, swipeFlags);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }
}