package com.emrhmrc.genericrecycler.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.emrhmrc.genericrecycler.BR;
import com.google.gson.annotations.Expose;

public class BaseModel extends BaseObservable {

    @Expose
    private String genericFilterText = "";
    @Expose
    private boolean genericChecked;


    public String getGenericFilterText() {
        return genericFilterText;
    }

    public void setGenericFilterText(String genericFilterText) {
        this.genericFilterText = genericFilterText;
    }

    @Bindable
    public boolean isGenericChecked() {
        return genericChecked;
    }

    public void setGenericChecked(boolean genericChecked) {
        this.genericChecked = genericChecked;
        notifyPropertyChanged(BR._all);
    }
}
