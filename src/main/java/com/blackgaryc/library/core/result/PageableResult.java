package com.blackgaryc.library.core.result;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageableResult<T> extends BaseResult {
    private long page;
    private long totalPage;
    private long totalResult;
    private long size;
    private List<T> data;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalResult() {
        return totalResult;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setTotalResult(long totalResult) {
        this.totalResult = totalResult;
    }

    @Override
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageableResult{" +
                "page=" + page +
                ", totalPage=" + totalPage +
                ", totalResult=" + totalResult +
                ", data=" + data +
                '}';
    }

    public PageableResult(Page<T> data) {
        this.data = data.getContent();
        this.totalResult = data.getTotalElements();
        this.page = data.getPageable().getPageNumber();
        this.totalPage = data.getTotalPages();
    }

    public PageableResult() {
    }

    public PageableResult(long page,long size, long totalPage, long totalResult, List<T> data) {
        this.page = page;
        this.totalPage = totalPage;
        this.totalResult = totalResult;
        this.data = data;
        this.size = size;
    }
}
