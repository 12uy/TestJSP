package com.dvduy.paging;

import com.dvduy.sort.Sorter;

public class PageRequest implements Pageble {

    private Integer page;
    private Integer maxItemInPage;
    private Sorter sorter;

    public Sorter getSorter() {
        if (this.sorter != null) {
            return this.sorter;
        }
        return null;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public PageRequest(Integer page, Integer maxItemInPage, Sorter sorter) {
        this.page = page;
        this.maxItemInPage = maxItemInPage;
        this.sorter = sorter;
    }


    @Override
    public Integer getPage() {
        return this.page;
    }

    @Override
    public Integer getOffset() {
        if (this.page != null && this.maxItemInPage != null) {
        return (this.page - 1) * this.maxItemInPage;
        }
        return null;
    }

    @Override
    public Integer getLimit() {
        return this.maxItemInPage;
    }
}
