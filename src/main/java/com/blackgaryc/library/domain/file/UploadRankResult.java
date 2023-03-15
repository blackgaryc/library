package com.blackgaryc.library.domain.file;

import java.io.Serializable;

public class UploadRankResult implements Serializable {
    private String name;
    private String avatar;
    private Integer total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public UploadRankResult(String name, Integer total) {
        this.name = name;
        this.total = total;
    }

    public UploadRankResult() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
