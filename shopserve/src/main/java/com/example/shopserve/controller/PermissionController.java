package com.example.shopserve.controller;

import com.example.shopserve.entity.Permission;
import com.example.shopserve.entity.Role;
import com.example.shopserve.result.Pagination;
import com.example.shopserve.result.Result;
import com.example.shopserve.service.PermissionService;
import com.example.shopserve.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/permission")
@CrossOrigin
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
//

    @PostMapping("/add")
    public Result<String> add(@RequestBody Permission permission) throws SQLException, ClassNotFoundException {
        Permission permission1;
        System.out.println(permission);

        permission1 = permissionService.queryByName(permission.getName());
        Permission permission2 = permissionService.queryByName(permission.getName());
        if (permission1 == null&&permission2==null) {
            System.out.println("权限名" + permission.getName());
            permissionService.insert(permission);
            return Result.ok(null, "添加成功");
        } else {
            return Result.error(404,"权限已存在！");
        }

    }
    @PostMapping("/update/{id}")
    public Result<String>  update(@RequestBody Permission permission, @PathVariable int id) throws SQLException, ClassNotFoundException {
        Permission user1;
        System.out.println(permission);
        user1 = permissionService.queryById(id);
        if (user1 == null) {
            return Result.error(500,"该用户不存在！");
        } else {
            if(permission.getId()==null) permission.setId(id);
            System.out.println("user的昵称" + permission.getName());
            permissionService.update(permission);
            return Result.ok(null, "修改成功");
        }

    }
    @PostMapping("/delete/{id}")
    public Result<String>  delete(@PathVariable int id) throws SQLException, ClassNotFoundException {
        Permission user1;
        System.out.println(id);
        user1 = permissionService.queryById(id);
        if (user1 == null) {
            return Result.error(500,"角色不存在！");
        } else {//删除权限，删除角色权限
            permissionService.deleteById(id);
            return Result.ok(null, "删除成功");
        }

    }
    @GetMapping("/all")
    public Result<Pagination<Permission>> getStarredMovies(int page){
        List<Permission> list = permissionService.getUserPage(page);
        int length=permissionService.count();
        Pagination pagi=Pagination.ok(list,length,page,20);
        return Result.ok(pagi);
    }
}
