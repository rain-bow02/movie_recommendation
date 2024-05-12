package com.example.shopserve.service.impl;

import com.example.shopserve.dao.RoleDao;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.User;
import com.example.shopserve.dao.UserDao;
import com.example.shopserve.service.UserService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-06-14 14:19:30
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param name 主键
     * @return 实例对象
     */
    @Override
    public User queryByName(String name) {
        return this.userDao.queryByName(name);
    }



    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        this.roleDao.deleteUser_role(id);
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public List<User> getUserPage(int page) {
        int offset=(page-1)*20;
        List<User> shopList = this.userDao.getUserPage(offset,20);
//        return shopList;
        return shopList;
    }

    @Override
    public int count() {
       return  this.userDao.count();
    }
}
