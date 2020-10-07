package com.emrhmrc.genericrecycler.interfaces;

public interface IOnItemClickListener<T extends BaseModel> extends IGenericBaseRecyclerListener {

    void onItemClicked(T item, int positon);
}