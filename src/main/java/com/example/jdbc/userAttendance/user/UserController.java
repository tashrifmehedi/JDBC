package com.example.jdbc.userAttendance.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/register")
    public String showRegisterPage() {
        return "userAttendance/register";
    }

    @PostMapping(value = "/register")
    public String saveUser(@ModelAttribute User user){
        userService.saveUser(user);
        return "redirect:/";
    }
}
