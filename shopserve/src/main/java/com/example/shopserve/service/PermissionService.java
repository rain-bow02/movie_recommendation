package com.example.shopserve.service;

import com.example.shopserve.entity.Permission;
import com.example.shopserve.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2023-06-14 14:19:30
 */
@Service
@Transactional
public interface PermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission queryById(Integer id);

    /**
     * 通过ID查询单条数据
     *
     * @param name 主键
     * @return 实例对象
     */
    Permission queryByName(String name);


    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Permission insert(Permission user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Permission update(Permission user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<Permission> getUserPage(int page);

    int count();

    void addUserRelation(User user);

    void insertRole_user(int user_id, int role_id);

    int deleteUser_role(int user_id);

    void deleteRole_user(int role_id);
}
