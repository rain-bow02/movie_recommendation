package com.example.shopserve.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role  implements Serializable {
    private Integer id;
    private String name;
    private String permission_id;

    public String getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(String permission_id) {
        this.permission_id = permission_id;
    }

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
}
