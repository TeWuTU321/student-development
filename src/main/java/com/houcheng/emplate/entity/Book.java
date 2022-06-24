package com.houcheng.emplate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * author:tutu
 * version:1.0
 */
@TableName("book")
@Data
public class Book {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String bookName;
    private BigDecimal price;
    private String author;
    //    日期格式化
    @JsonFormat(pattern = "yy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    @TableField(exist = false)
    private String userId;
    private String cover;

}
