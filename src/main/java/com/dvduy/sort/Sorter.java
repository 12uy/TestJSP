package com.dvduy.sort;

public class Sorter {
    private String SortBy;
    private String SortType;

    public Sorter(String sortBy, String sortType) {
        SortBy = sortBy;
        SortType = sortType;
    }

    public String getSortBy() {
        return SortBy;
    }

    public void setSortBy(String sortBy) {
        SortBy = sortBy;
    }

    public String getSortType() {
        return SortType;
    }

    public void setSortType(String sortType) {
        SortType = sortType;
    }
}
