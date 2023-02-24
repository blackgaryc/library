package com.blackgaryc.library.entity;

public enum BookUploadRequestStatusEnum {

    WAIT_PROCESS(0), CONFORMED(2),
    WAIT_REVIEW(1), REFUSED(3);

    /**
     * 0待处理 1待审核 2确认 3拒绝
     */
    private int code;

    public int getCode() {
        return code;
    }

    BookUploadRequestStatusEnum(int code) {
        this.code = code;
    }
}
