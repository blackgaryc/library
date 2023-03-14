package com.blackgaryc.library.tools.context;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rometools.utils.Strings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HttpContextTool {
    private static final String PAGE = "page";
    private static final String DEFAULT_PAGE = "1";
    private static final String SIZE = "size";
    private static final String DEFAULT_SIZE = "50";

    public static <T> Page<T> getDefaultPage(T clazz) {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Long page = getValueOrDefaultAsLong(httpServletRequest, PAGE, DEFAULT_PAGE);
        Long size = getValueOrDefaultAsLong(httpServletRequest, SIZE, DEFAULT_SIZE);
        //mybatis page is 1 based
        return Page.of(page, size);
    }

    public static <T> Page<T> getDefaultPage() {
        return getDefaultPage(null);
    }

    public static Pageable getDefaultPageable() {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        int page = getValueOrDefaultAsInteger(httpServletRequest, PAGE, DEFAULT_PAGE);
        if (page > 0) {
            page -= 1;
        }
        //Pageable is zero based
        Integer size = getValueOrDefaultAsInteger(httpServletRequest, SIZE, DEFAULT_SIZE);
        return PageRequest.of(page, size);
    }

    private static String getValueOrDefault(HttpServletRequest request, String key, String valueDefault) {
        String value = request.getParameter(key);
        return Strings.isEmpty(value) ? valueDefault : value;
    }

    private static Long getValueOrDefaultAsLong(HttpServletRequest request, String key, String valueDefault) {
        return Long.valueOf(getValueOrDefault(request, key, valueDefault));
    }

    private static Integer getValueOrDefaultAsInteger(HttpServletRequest request, String key, String valueDefault) {
        return Integer.valueOf(getValueOrDefault(request, key, valueDefault));
    }
}
