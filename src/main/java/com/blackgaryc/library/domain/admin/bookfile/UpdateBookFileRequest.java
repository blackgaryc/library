package com.blackgaryc.library.domain.admin.bookfile;

import java.util.Collections;
import java.util.List;

public class UpdateBookFileRequest {
    private List<Long> ids = Collections.emptyList();
    private int status = 1;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateBookFileRequest{" +
                "ids=" + ids +
                ", status=" + status +
                '}';
    }
}
