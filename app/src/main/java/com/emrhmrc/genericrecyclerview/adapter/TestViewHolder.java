package com.emrhmrc.genericrecyclerview.adapter;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.emrhmrc.genericrecycler.adapters.BaseViewHolder;
import com.emrhmrc.genericrecycler.interfaces.IOnItemClickListener;
import com.emrhmrc.genericrecycler.util.SwipeLayout;
import com.emrhmrc.genericrecyclerview.TestModel;
import com.emrhmrc.genericrecyclerview.databinding.ListItemLeftRightBinding;

public class TestViewHolder extends BaseViewHolder<TestModel,
        IOnItemClickListener<TestModel>> {
    private static final String TAG = "TestViewHolder";
    private ListItemLeftRightBinding binding;

    public TestViewHolder(ListItemLeftRightBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ListItemLeftRightBinding getBinding() {
        return binding;
    }

    @Override
    public void onBind(final TestModel item, final @Nullable IOnItemClickListener<TestModel> listener, int position) {
        if (getAbsoluteAdapterPosition() != RecyclerView.NO_POSITION) {
            binding.swipeLayout.setOffset(binding.swipeLayout.getOffset());
        }
        binding.setItem(item);
        binding.materialTextView.setOnClickListener(view ->
                listener.onItemClicked(item, position)
        );

        setSwipes(binding);
    }

    private void setSwipes(ListItemLeftRightBinding binding) {


        binding.swipeLayout.setOnSwipeListener(new SwipeLayout.OnSwipeListener() {
            @Override
            public void onBeginSwipe(SwipeLayout swipeLayout, boolean moveToRight) {
            }

            @Override
            public void onSwipeClampReached(SwipeLayout swipeLayout, boolean moveToRight) {
                Toast.makeText(swipeLayout.getContext(),
                        (moveToRight ? "Left" : "Right") + " clamp reached",
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onLeftStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {
                Log.d(TAG, "onLeftStickyEdge: " + moveToRight);
            }

            @Override
            public void onRightStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {
                Log.d(TAG, "onRightStickyEdge: " + moveToRight);
            }
        });
    }


}
