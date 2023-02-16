package com.blackgaryc.library.core.minio;

public enum ObjectKeyPrefixEnum {
    BOOK_COVER("cover/");
    private final String prefix;

    ObjectKeyPrefixEnum(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
