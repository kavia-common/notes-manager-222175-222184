package com.example.notesbackend.dto;

import java.util.List;

/**
 * Generic paged response wrapper.
 * @param <T> item type
 */
// PUBLIC_INTERFACE
public class PagedResponse<T> {
    private List<T> items;
    private int page;
    private int size;
    private long total;

    public PagedResponse() {}

    public PagedResponse(List<T> items, int page, int size, long total) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public PagedResponse<T> setItems(List<T> items) {
        this.items = items;
        return this;
    }

    public int getPage() {
        return page;
    }

    public PagedResponse<T> setPage(int page) {
        this.page = page;
        return this;
    }

    public int getSize() {
        return size;
    }

    public PagedResponse<T> setSize(int size) {
        this.size = size;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public PagedResponse<T> setTotal(long total) {
        this.total = total;
        return this;
    }
}
