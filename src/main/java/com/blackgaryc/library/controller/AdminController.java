package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.domain.admin.bookfile.UpdateBookFileRequest;
import com.blackgaryc.library.entity.BookUploadRequestStatusEnum;
import com.blackgaryc.library.myservice.AdminFileUploadService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("admin")
@SaCheckRole("admin")
public class AdminController {
    @Resource
    AdminFileUploadService adminFileUploadService;

    @GetMapping("book/file/upload")
    public BaseResult manageBookUploadRequest(@RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "10") Integer size,
                                              @RequestParam(defaultValue = "1") Integer status,
                                              Long id) {
        if (Objects.isNull(id)){
            return adminFileUploadService
                    .queryBookFileUploadRequest(BookUploadRequestStatusEnum.valueOf(status), Page.of(page, size));
        }
        return adminFileUploadService.queryBookFileUploadRequest(id);
    }

    @PostMapping("book/file/upload/update")
    public BaseResult updateBookUploadRequest(@RequestBody UpdateBookFileRequest request) {

        return adminFileUploadService.updateBookFileUploadRequest(request.getIds(),BookUploadRequestStatusEnum.valueOf(request.getStatus()));
    }
}
