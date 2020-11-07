package hu.suaf.springblog.controller;

import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.model.Role;
import hu.suaf.springblog.service.BloggerService;
import hu.suaf.springblog.service.ContactUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private ContactUserDetailsService contactUserDetailsService;
    private BloggerService bloggerService;

    PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(ContactUserDetailsService contactUserDetailsService, BloggerService bloggerService) {
        this.contactUserDetailsService = contactUserDetailsService;
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
    public String registerBlogger(Blogger blogger, BindingResult result){
        if(result.hasErrors()){
            return "register";
        }
        System.out.println(blogger.toString());

        bloggerService.saveBlogger(blogger);
        return "redirect:/login";
    }







}
