package hu.suaf.springblog.controller;

import hu.suaf.springblog.model.BlogPost;
import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/blogger")
public class BloggerController {

    @Autowired
    public BloggerController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    private BlogPostService blogPostService;

    @GetMapping("")
    public String listBlogPosts(Model model){
       // model.addAttribute("contacts", contactService.getContacts());
        return "home";
    }

    @GetMapping("/post-create")
    public String createBlogPostForm(BlogPost blogPost) {
        return "post-create";
    }

    @PostMapping("/post-create")
    public String createBlogPost(BlogPost blogPost, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/post-create";
        }
        //Blogger blogger = new Blogger();
        //blogger.setId(3);
        //blogPost.setAuthor(blogger);
        System.out.println(blogPost.toString());

        blogPostService.saveBlogPost(blogPost);
        return "redirect:/blogger";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }






    @GetMapping("/profile")
    public String viewProfile(Model model){
        return "profile";
    }



}
