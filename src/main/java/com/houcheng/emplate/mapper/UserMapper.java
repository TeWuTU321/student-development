package com.houcheng.emplate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houcheng.emplate.entity.Book;
import com.houcheng.emplate.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * author:tutu
 * version:1.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {


    List<Book> findBookByUId(Integer id);
}
