package hu.suaf.springblog.controller;

import hu.suaf.springblog.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class BlogPostController {

    private BlogPostService blogPostService;



    @GetMapping("")
    public String viewBlogPosts_get(Model model){
        return "post";
    }



}
