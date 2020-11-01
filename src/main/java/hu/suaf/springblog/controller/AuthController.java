package hu.suaf.springblog.controller;

import hu.suaf.springblog.model.ContactUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginForm(ContactUser contactUser) {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(ContactUser contactUser) {
        return "register";
    }







}
