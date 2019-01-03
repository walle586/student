package com.example.sims.domain;

public class ResponseData<T> {
    private long total;
    private int size;
    private int pages;
    private T table;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public T getTable() {
        return table;
    }

    public void setTable(T table) {
        this.table = table;
    }
}
