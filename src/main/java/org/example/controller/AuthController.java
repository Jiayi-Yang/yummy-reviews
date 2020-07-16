package org.example.controller;

import org.example.model.User;
import org.example.service.JWTService;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private JWTService jwtService;
    private String errorMsg = "The email or password is not correct.";
    private String tokenKeyWord = "Authorization";
    private String tokenType = "Bearer";

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Map<String, String> authentication(@RequestBody User user){
        logger.debug("username is " + user.getUsername());
        try{
            User u = userService.getUSerByCredentials(user.getUsername(), user.getPassword());
            String token = jwtService.generateToken(u);
            Map<String,String> t = new HashMap<>();
            t.put("token",token);
            return t;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
