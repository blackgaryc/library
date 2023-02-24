package com.blackgaryc.library.entity;

public enum BookUploadRequestStatusEnum {

    WAIT_PROCESS(0, "待处理"), CONFORMED(2, "已确认"),
    WAIT_REVIEW(1, "待审核"), REFUSED(3, "已拒绝");

    /**
     * 0待处理 1待审核 2确认 3拒绝
     */
    private final int code;
    private final String message;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    BookUploadRequestStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
