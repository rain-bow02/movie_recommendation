package com.example.shopserve.dao;

import com.example.shopserve.entity.Permission;
import com.example.shopserve.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-14 14:19:30
 */
@Mapper
public interface PermissionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission queryById(Integer id);

    /**
     * name
     *
     * @param name 主键
     * @return 实例对象
     */
    Permission queryByName(String name);
    /**
     * 统计总行数
     *
     * @param user 查询条件
     * @return 总行数
     */
    int count();

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(Permission user);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Role> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Role> entities);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(Permission user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<Permission> getUserPage(int offset, int size);

    void insertRole_user(int user_id, int role_id);

    int deleteRole_user(int role_id);

    int deleteUser_role(int user_id);

    void insertRole_permission(int role_id, int permission_id);

    int deleteRole_permission(int role_id);

    void deletePermission_role(int permission_id);
}

