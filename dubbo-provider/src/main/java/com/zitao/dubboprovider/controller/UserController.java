package com.zitao.dubboprovider.controller;


import com.zitao.dubbocommon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    @ResponseBody
    public String getUser(@RequestParam Integer userId) {
        return userService.getUser(userId);
    }
}
