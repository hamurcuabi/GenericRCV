package com.emrhmrc.genericrecycler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.emrhmrc.genericrecycler.interfaces.IGenericBaseRecyclerListener;
import com.emrhmrc.genericrecycler.models.BaseModel;

import java.util.ArrayList;
import java.util.List;


public abstract class GenericAdapter<T extends BaseModel, L extends IGenericBaseRecyclerListener, VH extends BaseViewHolder<T, L>> extends RecyclerView.Adapter<VH> {

    private List<T> items;
    private List<T> itemsFilter;
    private L listener;
    private LayoutInflater layoutInflater;
    private Context context;
    private ViewGroup emptyView;


    @Deprecated
    public GenericAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        items = new ArrayList<>();
        if (isEmpty())
            showEmptyView();
        else
            hideEmptyView();

    }

    public GenericAdapter(Context context, L listener, ViewGroup emptyView) {
        this.listener = listener;
        this.items = new ArrayList<>();
        this.itemsFilter = new ArrayList<>();
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.emptyView = emptyView;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (items.size() <= position) {
            return;
        }
        T item = items.get(position);
        holder.onBind(item, listener);

    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public int getFilterItemCount() {
        return itemsFilter != null ? itemsFilter.size() : 0;
    }

    public void setItems(List<T> items, boolean notifyChanges) throws IllegalArgumentException {
        if (items == null) {
            throw new IllegalArgumentException("Cannot set `null` item to the Recycler adapter");
        }
        this.items.clear();
        this.items.addAll(items);
        if (notifyChanges) {
            notifyDataSetChanged();
        }
        if (isEmpty())
            showEmptyView();
        else
            hideEmptyView();
    }

    public void updateItems(List<T> newItems) {
        setItems(newItems, false);
    }

    public void updateItems(List<T> newItems, DiffUtil.Callback diffCallback) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(diffCallback, false);
        setItems(newItems, false);
        result.dispatchUpdatesTo(this);
    }

    public List<T> getItemsFilter() {
        if (isFilterEmpty())
            showEmptyView();
        else
            hideEmptyView();
        return itemsFilter;
    }

    public void setItemsFilter(List<T> itemsFilter) {
        this.itemsFilter = itemsFilter;
        if (isFilterEmpty())
            showEmptyView();
        else
            hideEmptyView();
    }

    public List<T> getItems() {
        if (isEmpty())
            showEmptyView();
        else
            hideEmptyView();
        return items;
    }

    public void setItems(List<T> items) {

        setItems(items, true);
        setItemsFilter(items);
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to the Recycler adapter");
        }
        items.add(item);
        notifyItemInserted(items.size() - 1);

    }

    public void addToBeginning(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to the Recycler adapter");
        }
        items.add(0, item);
        notifyItemInserted(0);
        hideEmptyView();
    }

    public void addAll(List<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("Cannot add `null` items to the Recycler adapter");
        }
        this.items.addAll(items);
        notifyItemRangeInserted(this.items.size() - items.size(), items.size());
        hideEmptyView();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void remove(T item) {
        int position = items.indexOf(item);
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
        if (isEmpty())
            showEmptyView();
        else
            hideEmptyView();
    }

    public void remove(int position) {
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
            if (isEmpty())
                showEmptyView();
            else
                hideEmptyView();
        }
    }

    public boolean isEmpty() {

        return getItemCount() == 0;
    }

    public boolean isFilterEmpty() {
        return getFilterItemCount() == 0;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    public L getListener() {
        return listener;
    }

    public void setListener(L listener) {
        this.listener = listener;
    }

    @NonNull
    protected View inflate(@LayoutRes final int layout, @Nullable final ViewGroup parent, final boolean attachToRoot) {
        return layoutInflater.inflate(layout, parent, attachToRoot);
    }

    @NonNull
    protected View inflate(@LayoutRes final int layout, final @Nullable ViewGroup parent) {
        return inflate(layout, parent, false);
    }

    private void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    private void hideEmptyView() {
        emptyView.setVisibility(View.GONE);
    }


}
