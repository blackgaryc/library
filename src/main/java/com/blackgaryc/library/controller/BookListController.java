package com.blackgaryc.library.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blackgaryc.library.core.error.LibraryException;
import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.domain.book.SimpleBook;
import com.blackgaryc.library.domain.booklist.CreateBookListRequest;
import com.blackgaryc.library.domain.booklist.SimpleBookList;
import com.blackgaryc.library.domain.booklist.UpdateBookListRequest;
import com.blackgaryc.library.entity.BooklistEntity;
import com.blackgaryc.library.myservice.UserBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/book_list")
public class BookListController {
    @Autowired
    UserBookListService userBookListService;

    /**
     * 列出当前用户的书单
     *
     * @return
     */
    @SaCheckLogin
    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResult getUserBookList() {
        return Results.successMybatisPageData(userBookListService.listByPage());
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public BaseResult getInfo(@PathVariable Long id) {
        return Results.successData(userBookListService.getInfo(id));
    }
    /**
     * 更新单个书单信息
     *
     * @param request
     * @return
     */
    @SaCheckLogin
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public BaseResult updateUserBookList(@RequestBody UpdateBookListRequest request) throws LibraryException {
        userBookListService.update(request);
        return Results.success();
    }

    /**
     * 创建书单
     *
     * @param request
     * @return
     */
    @SaCheckLogin
    @RequestMapping(value = "", method = RequestMethod.POST)
    public BaseResult createUserBookList(@RequestBody CreateBookListRequest request) {
        userBookListService.create(request);
        return Results.success();
    }

    /**
     * 删除书单
     *
     * @param id
     * @return
     */
    @SaCheckLogin
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public BaseResult deleteUserBookList(@PathVariable String id) throws LibraryException {
        userBookListService.deleteBookList(id);
        return Results.success();
    }


    /**
     * 返回书单包含的书籍
     *
     * @param id book list id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @SaIgnore
    public BaseResult listBookInBookList(@PathVariable String id) throws LibraryException {
        Page<SimpleBook> data = userBookListService.listBookInBookList(id);
        return Results.successMybatisPageData(data);
    }

    /**
     * 为书单添加图书
     */
    @SaCheckLogin
    @RequestMapping(value = "{id}/{bookId}", method = RequestMethod.POST)
    public BaseResult addBook2BookList(@PathVariable String id, @PathVariable Long bookId) throws LibraryException {
        userBookListService.addBook2BookList(id, bookId);
        return Results.success();
    }

    /**
     * 删除书单中的书籍
     *
     * @param id
     * @return
     */
    @SaCheckLogin
    @RequestMapping(value = "{id}/{bookId}", method = RequestMethod.DELETE)
    public BaseResult deleteBookInBookList(@PathVariable String id,
                                           @PathVariable(name = "bookId") Long bookId) throws LibraryException {
        userBookListService.deleteBookInBookList(id, bookId);
        return Results.success();
    }

    @SaIgnore
    @RequestMapping(value = "select",method = RequestMethod.GET)
    public BaseResult getAllPublishedBookList(){
        Page<BooklistEntity> simpleBookLists=userBookListService.getAllPublishedBookList();
        return Results.successMybatisPageData(simpleBookLists,SimpleBookList::new);
    }
}
