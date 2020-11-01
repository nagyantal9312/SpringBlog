package hu.suaf.springblog.controller;

import hu.suaf.springblog.model.Blogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginForm(Blogger blogger) {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Blogger blogger) {
        return "register";
    }







}
