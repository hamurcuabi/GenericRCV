package com.emrhmrc.genericrecycler.interfaces;

import com.emrhmrc.genericrecycler.models.BaseModel;

public interface IOnItemClickListener<T extends BaseModel> extends IGenericBaseRecyclerListener {

    void onItemClicked(T item, int positon);
}