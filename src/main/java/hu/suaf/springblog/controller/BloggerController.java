package hu.suaf.springblog.controller;

import hu.suaf.springblog.model.BlogPost;
import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.service.BlogPostService;
import hu.suaf.springblog.service.BloggerService;
import hu.suaf.springblog.service.CategoryService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Converter;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Controller
@RequestMapping("/blogger")
public class BloggerController {

    private BlogPostService blogPostService;
    private CategoryService categoryService;
    private BloggerService bloggerService;


    @Autowired
    public BloggerController(BlogPostService blogPostService, CategoryService categoryService, BloggerService bloggerService) {
        this.blogPostService = blogPostService;
        this.categoryService = categoryService;
        this.bloggerService = bloggerService;
    }

/*

    @GetMapping("")
    public String listBlogPostsByCategory(Model model,@RequestParam("cim") String title){
        System.out.println(title);
       model.addAttribute("posztok", blogPostService.listBlogPosts());
        return "home";
    }
*/


    @GetMapping("")
    public String listBlogPosts_get(Model model,@RequestParam(value = "cim", required = false) String title){

        if(title == null){
            model.addAttribute("posztok", blogPostService.listBlogPosts());
        }else{
            model.addAttribute("posztok", blogPostService.searchBlogPostByTitle(title.strip()));
        }
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
        return "redirect:/blogger";
    }


    @GetMapping("/profile/{username}")
    public String createProfilePage(@PathVariable String username, Model model) {
        model.addAttribute("ezegyBlogger", bloggerService.findByUsername(username));
        return "profile";
    }

    @PostMapping("/profile/{username}")
    public String editProfile(@PathVariable String username, Blogger blogger, Model model){
        blogger.setPhoto(bloggerService.findByUsername(username).getPhoto());
        bloggerService.editBlogger(blogger);

        return "redirect:/blogger/profile/" + username;
    }

    @PostMapping("/profile/photo/{username}")
    public String editPhoto(@PathVariable String username, @RequestParam("image") MultipartFile image, Model model){

        Blogger blogger = bloggerService.findByUsername(username);
        bloggerService.uploadPhoto(blogger,image);

        return "redirect:/blogger/profile/" + username;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }






}
