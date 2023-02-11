package com.blackgaryc.library.core.result;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageableResult<T> extends BaseResult{
    private int page;
    private int totalPage;
    private long totalResult;
    private List<T> data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalResult() {
        return totalResult;
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
}
