package com.houcheng.emplate.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houcheng.emplate.common.Result;
import com.houcheng.emplate.entity.Book;
import com.houcheng.emplate.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * author:tutu
 * version:1.0
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookMapper bookMapper;

    //储存
    @PostMapping
    public Result<?> save(@RequestBody Book book) {

        bookMapper.insert(book);

        return Result.success();
    }

    //  更新
    @PutMapping
    public Result<?> update(@RequestBody Book book) {

        bookMapper.updateById(book);

        return Result.success();
    }

    //  查询
    @GetMapping
    public Result<?> findPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String search) {

        LambdaQueryWrapper<Book> wrapper = Wrappers.<Book>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Book::getBookName, search);
        }
        Page<Book> bookPage = bookMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        return Result.success(bookPage);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<?> delete(Long id){
        bookMapper.deleteById(id);
        return Result.success();
    }

    /**
     * 根据id数组批量删除
     * @param ids
     * @return
     */
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        bookMapper.deleteBatchIds(ids);
        return Result.success();
    }
}
