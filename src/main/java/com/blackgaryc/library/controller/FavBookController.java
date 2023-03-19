package com.blackgaryc.library.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.entity.BookEntity;
import com.blackgaryc.library.entity.BookFavListEntity;
import com.blackgaryc.library.service.BookFavListService;
import com.blackgaryc.library.service.BookService;
import com.blackgaryc.library.tools.context.HttpContextTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/fav")
@SaCheckLogin
public class FavBookController {
    @Autowired
    BookFavListService bookFavListService;
    @Autowired
    BookService bookService;
    /**
     * 添加喜欢的书
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public BaseResult add(@RequestParam(defaultValue = "") Long id){
        BookEntity byId = bookService.getById(id);
        if (Objects.isNull(byId)){
            throw new IllegalArgumentException("id not found");
        }
        BookFavListEntity bookFavListEntity = new BookFavListEntity();
        bookFavListEntity.setBookId(byId.getId());
        bookFavListEntity.setBookImg(byId.getThumbnail());
        bookFavListEntity.setBookName(byId.getTitle());
        bookFavListEntity.setUid(StpUtil.getLoginIdAsLong());
        try {
            bookFavListService.save(bookFavListEntity);
        }catch (DuplicateKeyException e){
            return Results.errorMessage("已经保存");
        }
        return Results.success();
    }

    /**
     * 从喜欢的书删除
     * @return
     */
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable Long id){
        BookFavListEntity one = bookFavListService.lambdaQuery()
                .eq(BookFavListEntity::getBookId, id)
                .eq(BookFavListEntity::getUid, StpUtil.getLoginIdAsLong())
                .one();
        if (Objects.isNull(one)){
            return Results.errorMessage("不存在");
        }
        bookFavListService.removeById(one.getId());
        return Results.success();
    }

    /**
     * 查询喜欢的书
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResult list(){
        return Results.successMybatisPageData(bookFavListService.page(HttpContextTool.getDefaultPage()));
    }
}
