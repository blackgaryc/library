package com.blackgaryc.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blackgaryc.library.entity.AuthorEntity;
import com.blackgaryc.library.service.AuthorService;
import com.blackgaryc.library.mapper.AuthorMapper;
import org.springframework.stereotype.Service;

/**
* @author blackgaryc
* @description 针对表【author】的数据库操作Service实现
* @createDate 2023-01-27 12:35:59
*/
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, AuthorEntity>
    implements AuthorService{

}




