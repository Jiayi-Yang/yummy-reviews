package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    // {prefix}/user/1 GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(name = "id") Long id){
        return userService.getBy(id);
    }

    // {prefix}/user/?username=rhang GET
    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"username"})
    public User getUserByUsername(@RequestParam(name = "username") String username){
        return userService.getUserByUsername(username);
    }

    // {prefix}/user/99?username=user0824 PATCH
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public User updateUser(@PathVariable(name = "id") Long id, @RequestParam("username") String username){
        User user = userService.getBy(id);
        user.setUsername(username);
        user = userService.update(id, user);
        return user;
    }

    // {prefix}/user/99 PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable(name = "id") Long id, @RequestBody User updatedUser){
        updatedUser = userService.update(id, updatedUser);
        return updatedUser;
    }
    // {prefix}/user POST
    @RequestMapping(value = "", method = RequestMethod.POST)
    public User create(@RequestBody User newUser){
        User user = userService.save(newUser);
        return user;
    }
}
