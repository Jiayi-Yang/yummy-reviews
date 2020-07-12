package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping(value = "/user/")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

//    // {prefix}/user/
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public List<User> getUsers(){
//        return userService.getUsers();
//    }
    // {prefix}/user/1 GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(name = "id") Long id){
        return userService.getBy(id);
    }

    // {prefix}//user/?username=testuser GET
    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"username"})
    public User getUserByUsername(@RequestParam(name = "username") String username){
        return userService.getUserByUsername(username);
    }

    // {prefix}//user/41?username=newtest PATCH
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public User updateUser(@PathVariable(name = "id") Long id, @RequestParam("username") String username){
        User user = userService.getBy(id);
        user.setUsername(username);
        user = userService.update(user);
        return user;
    }

    // {prefix}//user/43 PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable(name = "id") Long id, @RequestBody User updatedUser){
        updatedUser.setUserId(id);
        updatedUser = userService.update(updatedUser);
        return updatedUser;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public User create(@RequestBody User newUser){
        User user = userService.save(newUser);
        return user;
    }
}
