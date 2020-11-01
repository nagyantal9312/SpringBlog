package hu.suaf.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogger")
public class BloggerController {


    @GetMapping("")
    public String listBlogPosts(Model model){
       // model.addAttribute("contacts", contactService.getContacts());
        return "home";
    }

    @GetMapping("/post-create")
    public String createBlogPosts(Model model) {
        return "post-create";
    }

    @GetMapping("/profile")
    public String viewProfile(Model model){
        return "profile";
    }



}
