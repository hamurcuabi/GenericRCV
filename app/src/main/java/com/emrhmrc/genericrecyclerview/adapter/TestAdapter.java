package com.emrhmrc.genericrecyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.databinding.DataBindingUtil;

import com.emrhmrc.genericrecycler.adapters.BaseFilterAdapter;
import com.emrhmrc.genericrecycler.adapters.GenericAdapter;
import com.emrhmrc.genericrecycler.interfaces.IOnItemClickListener;
import com.emrhmrc.genericrecyclerview.R;
import com.emrhmrc.genericrecyclerview.TestModel;
import com.emrhmrc.genericrecyclerview.databinding.ItemTestBinding;

public class TestAdapter extends GenericAdapter<TestModel,
        IOnItemClickListener<TestModel>,
        TestViewHolder> implements Filterable {


    public TestAdapter(Context context, IOnItemClickListener listener, ViewGroup emptyView) {
        super(context, listener, emptyView);
    }

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTestBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                viewType, parent, false);
        return new TestViewHolder(binding);

    }


    @Override
    public int getItemViewType(int position) {
        final TestModel item = getItem(position);
        return R.layout.item_test;
    }

    @Override
    public Filter getFilter() {
        return new BaseFilterAdapter<>(this, this.getItemsFilter());
    }


}
