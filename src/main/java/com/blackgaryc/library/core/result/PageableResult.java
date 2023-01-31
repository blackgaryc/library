package com.blackgaryc.library.core.result;

import java.util.List;

public class PageableResult extends BaseResult{
    private int page;
    private int totalPage;
    private int totalResult;
    private List<Object> data;

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

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    @Override
    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
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
}
