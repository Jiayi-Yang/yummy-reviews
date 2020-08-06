package org.example.controller;

import org.example.model.Role;
import org.example.model.User;
import org.example.service.RoleService;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRoleController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    // {prefix}/user/
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public List<User> getUsers(){
        return userService.getUsers();
    }
    // {prefix}/role/
    @RequestMapping(value = "/role/", method = RequestMethod.GET)
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

}
