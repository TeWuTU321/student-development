package com.houcheng.emplate.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.houcheng.emplate.common.Result;
import com.houcheng.emplate.entity.News;
import com.houcheng.emplate.mapper.NewsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    NewsMapper newsMapper;

    /**
     * 储存
     * @param news
     * @return
     */
    @PostMapping
    public Result<?> save(@RequestBody News news) {
        news.setTime(new Date());
        newsMapper.insert(news);
        return Result.success();
    }

    /**
     * 更新
     * @param news
     * @return
     */
    @PutMapping
    public Result<?> update(@RequestBody News news) {
        newsMapper.updateById(news);
        return Result.success();
    }

    /**
     * 依据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id) {
        newsMapper.deleteById(id);
        return Result.success();
    }

    /**
     * 依据id进行查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(newsMapper.selectById(id));
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<News> wrapper = Wrappers.<News>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(News::getTitle, search);
        }
        Page<News> newsPage = newsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(newsPage);
    }
}
