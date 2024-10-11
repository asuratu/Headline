package com.heima.controller;


import com.heima.pojo.User;
import com.heima.service.UserService;
import com.heima.utils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author asura
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/info")
    public Result<?> getUserInfo(@RequestHeader("token") String token) {
        return userService.getUserInfo(token);
    }
}
