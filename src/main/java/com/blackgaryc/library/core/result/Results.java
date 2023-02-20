package com.blackgaryc.library.core.result;

public class Results {
    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "成功";
    private static final int UN_KNOW_ERROR_CODE = -1;

    public static BaseResult successData(Object data) {
        return BaseResult.Builder.result()
                .code(SUCCESS_CODE)
                .timestamp(System.currentTimeMillis())
                .data(data)
                .message(SUCCESS_MESSAGE)
                .build();
    }

    public static BaseResult successDataWithMessage(Object data,String message) {
        return BaseResult.Builder.result()
                .code(SUCCESS_CODE)
                .timestamp(System.currentTimeMillis())
                .data(data)
                .message(message)
                .build();
    }

    public static BaseResult success() {
        return BaseResult.Builder.result()
                .code(SUCCESS_CODE)
                .timestamp(System.currentTimeMillis())
                .message(SUCCESS_MESSAGE)
                .build();
    }

    public static BaseResult successMessage(String message) {
        return BaseResult.Builder.result()
                .code(SUCCESS_CODE)
                .timestamp(System.currentTimeMillis())
                .message(message)
                .build();
    }

    public static BaseResult errorMessage(String message) {
        return BaseResult.Builder.result()
                .code(UN_KNOW_ERROR_CODE)
                .timestamp(System.currentTimeMillis())
                .message(message)
                .build();
    }

    public static BaseResult errorMessageWithCode(int code, String message) {
        return BaseResult.Builder.result()
                .code(code)
                .timestamp(System.currentTimeMillis())
                .message(message)
                .build();
    }
}
