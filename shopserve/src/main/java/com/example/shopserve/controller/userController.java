package com.example.shopserve.controller;


import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.User;
import com.example.shopserve.result.Pagination;
import com.example.shopserve.service.RoleService;
import com.example.shopserve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.shopserve.result.Result;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class userController {
    @Autowired
    private UserService userService;
    @Autowired
    private  RoleService roleService;
//

    @PostMapping("/login")
    public Result<User> login(String name , String password) throws SQLException, ClassNotFoundException {
        User user;
        try{
            user= userService.queryByName(name);
            if(user.getPassword().equals(password)){
                return Result.ok(user, "登录成功");
            }
            else{
                return Result.error(500,"密码错误，请重新输入！");
            }
        }catch (Exception e){
            return Result.error(404,"登录失败！");
        }
    }

    @GetMapping("/userShow/{id}")
    public Result<User> queryUserDetail(@PathVariable("id") Integer userId){
        System.out.println(userService.queryById(userId));
        return Result.ok(userService.queryById(userId), "查看信息");
    }
    @PostMapping("/register")
    public Result<String>  register(@RequestBody User user) throws SQLException, ClassNotFoundException {
        User user1;
        System.out.println(user);

            user1 = userService.queryByName(user.getName());
            if (user1 == null) {
                System.out.println("user的昵称" + user.getName());
                userService.insert(user);
                return Result.ok("注册成功！", "注册成功");
            } else {
                return Result.error(404,"该用户已存在！");
            }

    }
    @PostMapping("/add")
    public Result<User>  add(@RequestBody User user) throws SQLException, ClassNotFoundException {
        User user1;
        System.out.println(user);

        user1 = userService.queryByName(user.getName());
        if (user1 == null) {
            System.out.println("user的昵称" + user.getName());
            userService.insert(user);
            User user2 = userService.queryByName(user.getName());
            if(user2!=null&&user.getRole_id()!=null) {
                roleService.addUserRelation(user2);
            }
            return Result.ok(user2, "添加成功");
        } else {
            return Result.error(404,"该用户已存在！");
        }

    }
    @PostMapping("/update/{id}")
    public Result<User>  update(@RequestBody User user, @PathVariable int id) throws SQLException, ClassNotFoundException {
        User user1;
        System.out.println(user);
        user1 = userService.queryById(id);
        if (user1 == null) {
            return Result.error(500,"该用户不存在！");
        } else {
            if(user.getId()==null) {
                user.setId(id);
            }
            userService.update(user);
            User user2 = userService.queryByName(user.getName());
            if(user.getRole_id()!=null)
                roleService.addUserRelation(user2);
//            return Result.ok(user2, "添加成功");
            return Result.ok(user2, "修改成功");
        }

    }
    @PostMapping("/delete/{id}")
    public Result<String>  delete(@PathVariable int id) throws SQLException, ClassNotFoundException {
        User user1;
        System.out.println(id);
        user1 = userService.queryById(id);
        if (user1 == null) {
            return Result.error(500,"该用户不存在！");
        } else {
            userService.deleteById(id);
            return Result.ok(null, "删除成功");
        }

    }
    //3得到某一位用户收藏过的所有电影
    @GetMapping("/all")
    public Result<Pagination<User>> getAllUser(int page){
        List<User> list = userService.getUserPage(page);
        int length=userService.count();
        Pagination pagi=Pagination.ok(list,length,page,20);
        return Result.ok(pagi);
    }
    /**
     * 根据userid查找user数据
     *
     * @param  id
     * @return 实例对象
     */
    @PostMapping("/idInfo/{id}")
    public Result<User> idInfo( @PathVariable int id)  {
        User user= this.userService.queryById(id);
        if(user!=null) {
            return Result.ok(user);
        }else{
            return Result.error(500,"该用户不存在");
        }
    }
}




