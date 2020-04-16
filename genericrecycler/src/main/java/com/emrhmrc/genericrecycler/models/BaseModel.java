package com.emrhmrc.genericrecycler.models;

import com.google.gson.annotations.Expose;

public class BaseModel {

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

    public boolean isGenericChecked() {
        return genericChecked;
    }

    public void setGenericChecked(boolean genericChecked) {
        this.genericChecked = genericChecked;
    }
}
