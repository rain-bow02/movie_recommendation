package com.example.shopserve.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class Permission   implements Serializable {
    private Integer id;
    private String name;
    private String  code_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }
}
