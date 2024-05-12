package com.example.shopserve.service.impl;

import com.example.shopserve.dao.RoleDao;
import com.example.shopserve.entity.Role;
import com.example.shopserve.entity.User;
import com.example.shopserve.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2023-06-14 14:19:30
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Integer id) {
        return this.roleDao.queryById(id);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param name 主键
     * @return 实例对象
     */
    @Override
    public Role queryByName(String name) {
        return this.roleDao.queryByName(name);
    }



    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role user) {
        this.roleDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role user) {
        this.roleDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer role_id) {
        this.deleteRole_user(role_id);
        return this.roleDao.deleteById(role_id) > 0;
    }

    @Override
    public List<Role> getUserPage(int page) {
        int offset=(page-1)*20;
        List<Role> shopList = this.roleDao.getUserPage(offset,20);
//        return shopList;
        return shopList;
    }

    @Override
    public int count() {
       return  this.roleDao.count();
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
        this.roleDao.insertRole_user(user_id, role_id);
    }

    @Override
    public int deleteUser_role(int user_id) {
        return this.roleDao.deleteUser_role(user_id);
    }
    @Override
    public void deleteRole_user(int role_id) {
        this.roleDao.deleteRole_user(role_id);
    }

}
