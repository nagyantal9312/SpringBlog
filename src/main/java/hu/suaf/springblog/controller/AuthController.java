package hu.suaf.springblog.controller;

import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.service.BloggerService;
import hu.suaf.springblog.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final BloggerService bloggerService;


    @Autowired
    public AuthController(UserDetailsServiceImpl userDetailsServiceImpl, BloggerService bloggerService) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.bloggerService = bloggerService;
    }



    @GetMapping("/login")
    public String loginForm(Blogger blogger) {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Blogger blogger) {
        return "register";
    }


    @PostMapping("/register")
    public String registerBlogger(@Valid Blogger blogger, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        bloggerService.registerBlogger(blogger);
        return "redirect:/login";
    }







}
