package com.blackgaryc.library.core.result;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    /**
     *
     * @param pageResult 分页数据
     * @param mapper 通过stream.map 将原始数据进行转换
     * @return
     * @param <T>
     * @param <R>
     */
    public static <T,R> PageableResult<R> successMybatisPageData(Page<T> pageResult, Function<? super T, ? extends R> mapper) {
        List<R> collect = pageResult.getRecords().stream().map(mapper).collect(Collectors.toList());
        return new PageableResult<>(pageResult.getCurrent(), pageResult.getSize(), pageResult.getPages(), pageResult.getTotal(), collect);
    }

    public static <T> PageableResult<T> successMybatisPageData(Page<T> pageResult) {
        return new PageableResult<>(pageResult.getCurrent(),pageResult.getSize(), pageResult.getPages(), pageResult.getTotal(), pageResult.getRecords());
    }
    public static <T> PageableResult<T> successSpringbootPageData(org.springframework.data.domain.Page<T> page){
        return new PageableResult<>(page.getPageable().getPageNumber(),page.getSize(    ), page.getTotalPages(), page.getTotalElements(),page.getContent());
    }
}
