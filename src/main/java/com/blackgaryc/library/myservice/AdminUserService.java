package com.blackgaryc.library.myservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.entity.UserEntity;

public interface AdminUserService {
    Page<UserEntity> getPageList(String name);

    void disableUser(Long id) throws LibraryException;
}
