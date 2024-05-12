package com.example.shopserve.service.impl;

import com.example.shopserve.dao.PermissionDao;
import com.example.shopserve.entity.Permission;
import com.example.shopserve.entity.User;
import com.example.shopserve.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2023-06-14 14:19:30
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Permission queryById(Integer id) {
        return this.permissionDao.queryById(id);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param name 主键
     * @return 实例对象
     */
    @Override
    public Permission queryByName(String name) {
        return this.permissionDao.queryByName(name);
    }



    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public Permission insert(Permission user) {
        this.permissionDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public Permission update(Permission user) {
        this.permissionDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param role_id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer permission_id) {
        this.deleteRole_user(permission_id);
        return this.permissionDao.deleteById(permission_id) > 0;
    }

    @Override
    public List<Permission> getUserPage(int page) {
        int offset=(page-1)*20;
        List<Permission> shopList = this.permissionDao.getUserPage(offset,20);
//        return shopList;
        return shopList;
    }

    @Override
    public int count() {
       return  this.permissionDao.count();
    }

    @Override
    public void addUserRelation(User user) {
        String[] roles;
        int user_id=user.getId();
        if (user.getRole_id().contains("|")) {
            roles = user.getRole_id().split("\\|");
        } else roles = new String[]{user.getRole_id()};
        for (int index = 0; index < roles.length; index++) {
            int typeId=Integer.parseInt(roles[index]);
            System.out.println(user_id + "movieId" + roles[0]);
            System.out.println("新增电影_类别" + user_id + typeId);
            //新增电影_类型 电影id 类别id
            insertRole_user(user_id, typeId);
        }
    }
    @Override
    public void insertRole_user(int user_id, int role_id) {
        int relation=deleteUser_role(user_id);
        System.out.println("删除了" +relation);
        this.permissionDao.insertRole_user(user_id, role_id);
    }

    @Override
    public int deleteUser_role(int user_id) {
        return this.permissionDao.deleteUser_role(user_id);
    }
    @Override
    public void deleteRole_user(int role_id) {
        this.permissionDao.deleteRole_user(role_id);
    }

}
