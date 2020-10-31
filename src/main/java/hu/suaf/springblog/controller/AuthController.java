package hu.suaf.springblog.controller;

import hu.suaf.springblog.model.ContactBlogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginForm(ContactBlogger contactBlogger){
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(ContactBlogger contactBlogger){
        return "register";
    }







}
