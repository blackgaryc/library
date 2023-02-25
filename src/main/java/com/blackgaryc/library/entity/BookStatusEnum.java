package com.blackgaryc.library.entity;

public enum BookStatusEnum {
    DEFAULT(0,"待审核"),
    ENABLE(1,"状态正常"),
    DISABLE(2,"被下架"),
    DELETED(3,"已删除");
    private final String message;

    private final int code;

    BookStatusEnum(int code,String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
