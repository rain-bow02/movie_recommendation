package com.example.shopserve.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2023-06-14 14:19:30
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 735277902774501886L;
    /**
     * 用户表
     */
    private Integer id;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 头像
     */
    private String password;
    /**
     * 手机号
     */
    private String email;
    /**
     * 密码
     */
    private String title;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }
}

