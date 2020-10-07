package com.emrhmrc.genericrecycler.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.emrhmrc.genericrecycler.interfaces.BaseModel;
import com.emrhmrc.genericrecycler.interfaces.IGenericBaseRecyclerListener;
import com.emrhmrc.genericrecycler.interfaces.ItemDragHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class GenericAdapter<
        T extends BaseModel,
        L extends IGenericBaseRecyclerListener,
        VH extends BaseViewHolder<T, L>
        >
        extends RecyclerView.Adapter<VH> implements ItemDragHelper {

    private List<T> items;
    private List<T> itemsFilter;
    private L listener;
    private Context context;
    private ViewGroup emptyView;

    public GenericAdapter(Context context, L listener, @Nullable ViewGroup emptyView) {
        this.listener = listener;
        this.items = new ArrayList<>();
        this.itemsFilter = new ArrayList<>();
        this.context = context;
        this.emptyView = emptyView;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        T item = items.get(position);
        holder.onBind(item, listener, position);
    }

    @Override
    abstract public int getItemViewType(int position);

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    public Context getContext() {
        return context;
    }

    public void updateItem(T item, int position) {
        notifyItemChanged(position, item);
    }

    public void updateItem(int position) {
        notifyItemChanged(position);
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
        setVisibiltity();
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
        setVisibiltity();
        return itemsFilter;
    }

    public void setItemsFilter(List<T> itemsFilter) {
        this.itemsFilter = itemsFilter;
        setVisibiltity();
    }

    private void addItemToFilter(T item) {
        this.itemsFilter.add(item);
        setVisibiltity();
    }

    private void removeItemToFilter(T item) {
        this.itemsFilter.remove(item);
        setVisibiltity();
    }

    public List<T> getItems() {
        setVisibiltity();
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
        addItemToFilter(item);
        notifyItemInserted(items.size() - 1);

    }

    public void addToPlace(T item, int place) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to the Recycler adapter");
        }
        items.add(place, item);
        notifyItemInserted(place);
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
        setVisibiltity();
    }

    public void remove(int position) {
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
            setVisibiltity();
            removeItemToFilter(items.get(position));
        }
    }

    public boolean isEmpty() {

        return getItemCount() == 0;
    }

    public boolean isFilterEmpty() {
        return getFilterItemCount() == 0;
    }

    private void showEmptyView() {
        if (emptyView != null)
            emptyView.setVisibility(View.VISIBLE);
    }

    private void hideEmptyView() {
        if (emptyView != null)
            emptyView.setVisibility(View.GONE);
    }

    private void setVisibiltity() {
        if (isEmpty())
            showEmptyView();
        else
            hideEmptyView();
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(items, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(items, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onRowSelected(BaseViewHolder myViewHolder) {
        myViewHolder.itemView.setBackgroundColor(Color.GRAY);

    }

    @Override
    public void onRowClear(BaseViewHolder myViewHolder) {
        myViewHolder.itemView.setBackgroundColor(Color.WHITE);

    }

}
