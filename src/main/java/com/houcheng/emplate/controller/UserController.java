package com.houcheng.emplate.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houcheng.emplate.common.Result;
import com.houcheng.emplate.entity.Book;
import com.houcheng.emplate.entity.User;
import com.houcheng.emplate.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * author:tutu
 * version:1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    //储存
    @PostMapping
    public Result<?> save(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword(SecureUtil.md5("123456"));
            user.setRole(2);
        }

        userMapper.insert(user);

        return Result.success();
    }

    //  更新
    @PutMapping
    public Result<?> update(@RequestBody User user) {

        userMapper.updateById(user);

        return Result.success();
    }

    //  查询
    @GetMapping
    public Result<?> findPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String search) {

        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(User::getNickName, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        return Result.success(userPage);
    }

    //    删除
    @DeleteMapping
    public Result<?> delete(Long id) {
        userMapper.deleteById(id);
        return Result.success();
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result<?> login(@RequestBody String userInfo) {
        JSONObject json = new JSONObject(userInfo);
        String verifyKey = json.get("verifyKey", String.class);
        String verifyCode = json.get("verifyCode", String.class);
        User user = new User();
        user.setUsername(json.get("username",String.class));
        user.setPassword(json.get("password",String.class));
        if (StringUtils.isBlank(verifyKey)) {
            return Result.error("-1", "验证码不为空");
        }
        String value = (String) redisTemplate.opsForValue().get(verifyKey);
        // 验证码已过期
        if (null == value) {
            return Result.error("-1", "验证码已过期，请刷新后重试");
            //说明是用户乱填或者有缓存
        } else if (!verifyCode.equalsIgnoreCase(value)) {
            return Result.error("-1", "无效的验证码，请刷新或输入正确的验证码");
        }
            User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, SecureUtil.md5(user.getPassword())));
            if (res == null) {
                return Result.error("401", "用户名或密码错误！");
            }
            return Result.success(res);


    }




    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (res != null) {
            return Result.error("-1", "用户已存在");
        }
        if (user.getPassword() == null) {
            user.setPassword(SecureUtil.md5("123456"));
        }else {
            user.setPassword(SecureUtil.md5(user.getPassword()));
        }


        userMapper.insert(user);
        return Result.success();

    }

    /**
     * 寻找当前登录的用户id
     */
    @PostMapping("/userInfo/{id}")
    public Result<?> userInfo(@PathVariable Long id) {
        if (id == null) {
            return Result.error("-1", "出现错误，程序小哥正在抢修");
        }
        User user = userMapper.selectById(id);
        return Result.success(user);
    }

    /**
     * 用户分页列表查询，包含书籍表的一对多查询
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/findBookList")
    public Result<?> findBookList(@RequestParam(defaultValue = "") Integer id) {
        System.out.println(id);
        if (id == null ){
            return Result.error("-1","未知错误，请程序员尽快抢修");
        }
        List<Book> books =  userMapper.findBookByUId(id);
        return Result.success(books);
    }
}
