package com.example.shopserve.controller;


import com.example.shopserve.entity.Role;
import com.example.shopserve.entity.User;
import com.example.shopserve.result.Pagination;
import com.example.shopserve.result.Result;
import com.example.shopserve.service.PermissionService;
import com.example.shopserve.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/add")
    public Result<String>  add(@RequestBody Role role) throws SQLException, ClassNotFoundException {
        Role user1;
        System.out.println(role);

        user1 = roleService.queryByName(role.getName());
        if (user1 == null) {
           int id= roleService.insert(role);
            System.out.println("id" + id);
            Role user2 = roleService.queryById(id);
            if(user2!=null&&role.getPermission_id()!=null) {
                permissionService.addRoleRelation(role);
            }
            return Result.ok(null, "添加成功");
        } else {
            return Result.error(404,"角色已存在！");
        }

    }
    @PostMapping("/update/{id}")
    public Result<String>  update(@RequestBody Role role, @PathVariable int id) throws SQLException, ClassNotFoundException {
        Role user1;
        System.out.println(role);
        user1 = roleService.queryById(id);
        if (user1 == null) {
            return Result.error(500,"该角色不存在！");
        } else {
            if(role.getId()==null) role.setId(id);
            System.out.println("user的昵称" + role.getName());
            roleService.update(role);
            return Result.ok(null, "修改成功");
        }

    }
    @PostMapping("/delete/{id}")
    public Result<String>  delete(@PathVariable int id) throws SQLException, ClassNotFoundException {
        Role user1;
        System.out.println(id);
        user1 = roleService.queryById(id);
        if (user1 == null) {
            return Result.error(500,"角色不存在！");
        } else {//删除角色，删除角色用户，删除角色权限
            roleService.deleteById(id);
            return Result.ok(null, "删除成功");
        }

    }
    @GetMapping("/all")
    public Result<Pagination<Role>> getStarredMovies(int page){
        List<Role> list = roleService.getUserPage(page);
        int length=roleService.count();
        Pagination pagi=Pagination.ok(list,length,page,20);
        return Result.ok(pagi);
    }
}




