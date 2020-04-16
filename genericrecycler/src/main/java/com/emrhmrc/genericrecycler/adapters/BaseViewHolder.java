package com.emrhmrc.genericrecycler.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.emrhmrc.genericrecycler.interfaces.IGenericBaseRecyclerListener;

import java.util.List;

public abstract class BaseViewHolder<T, L extends IGenericBaseRecyclerListener> extends RecyclerView.ViewHolder {

    private L listener;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void onBind(T item, @Nullable L listener);

    public void onBind(T item, List<Object> payloads) {
        onBind(item, listener);
    }

    protected L getListener() {
        return listener;
    }

}