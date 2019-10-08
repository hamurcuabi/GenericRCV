package com.emrhmrc.genericrecyclerview;

import com.emrhmrc.genericrecycler.models.BaseModel;

public class TestModel extends BaseModel {

    private int Id;
    private String header;
    private int count;

    public TestModel(int Id, String header, int count) {
        this.header = header;
        this.count = count;
        this.Id = Id;
    }

    public TestModel() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
