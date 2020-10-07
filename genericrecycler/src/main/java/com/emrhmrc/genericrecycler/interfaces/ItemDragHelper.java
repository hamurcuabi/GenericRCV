package com.emrhmrc.genericrecycler.interfaces;

import com.emrhmrc.genericrecycler.adapters.BaseViewHolder;

/**
 * Created by hamurcuabi on 08,October,2020
 **/
public interface ItemDragHelper {
    void onRowMoved(int fromPosition, int toPosition);

    void onRowSelected(BaseViewHolder baseViewHolder);

    void onRowClear(BaseViewHolder baseViewHolder);
}
