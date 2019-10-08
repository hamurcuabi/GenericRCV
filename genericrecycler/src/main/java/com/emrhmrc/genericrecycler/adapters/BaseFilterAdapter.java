package com.emrhmrc.genericrecycler.adapters;

import android.widget.Filter;

import com.emrhmrc.genericrecycler.models.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 */
public class BaseFilterAdapter<T extends BaseModel> extends Filter {
    List<T> filterList;
    GenericAdapter adapter;

    public BaseFilterAdapter(GenericAdapter adapter,
                             List<T> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toLowerCase();
            List<T> filtered = new ArrayList<>();

            for (int i = 0; i < adapter.getItemsFilter().size(); i++) {
                if (((T) adapter.getItemsFilter().get(i)).getFilterName().toLowerCase().contains(constraint)) {
                    //ADD DATA TO FILTERED DATA
                    filtered.add((T) adapter.getItemsFilter().get(i));
                }

            }

            results.count = filtered.size();
            results.values = filtered;
        } else {
            results.count = adapter.getItemsFilter().size();
            results.values = adapter.getItemsFilter();

        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.setItems((List<T>) results.values, true);
    }
}