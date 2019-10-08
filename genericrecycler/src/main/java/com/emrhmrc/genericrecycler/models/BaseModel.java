package com.emrhmrc.genericrecycler.models;

import com.google.gson.annotations.Expose;

public class BaseModel {

    @Expose
    private String filterName = "";
    @Expose
    private boolean checked;

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
