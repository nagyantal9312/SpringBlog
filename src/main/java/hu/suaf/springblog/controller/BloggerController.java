package hu.suaf.springblog.controller;

import hu.suaf.springblog.model.BlogPost;
import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.service.BlogPostService;
import hu.suaf.springblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/blogger")
public class BloggerController {

    private BlogPostService blogPostService;
    private CategoryService categoryService;


    @Autowired
    public BloggerController(BlogPostService blogPostService, CategoryService categoryService) {
        this.blogPostService = blogPostService;
        this.categoryService = categoryService;
    }



    @GetMapping("")
    public String listBlogPosts_get(Model model){
        model.addAttribute("posztok", blogPostService.listBlogPosts());
        return "home";
    }

    @PostMapping("")
    public String listBlogPosts_post(){

        return "home";
    }

    @GetMapping("/post-create")
    public String createBlogPostForm(Model model, BlogPost blogPost) {
        model.addAttribute("kategoriak", categoryService.listCategories());
        return "post-create";
    }

    @PostMapping("/post-create")
    public String createBlogPost(Model model, BlogPost blogPost, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/post-create";
        }
        blogPostService.saveBlogPost(blogPost);
        return "redirect:/blogger/profile";
    }


    @GetMapping("/profile")
    public String createProfilePage(@AuthenticationPrincipal Blogger blogger, Model model) {
        model.addAttribute("loggedInBlogger", blogger);
        return "profile";
    }




    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }






}
