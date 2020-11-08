package hu.suaf.springblog.controller;

import hu.suaf.springblog.model.BlogPost;
import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.model.Category;
import hu.suaf.springblog.model.Comment;
import hu.suaf.springblog.service.BlogPostService;
import hu.suaf.springblog.service.BloggerService;
import hu.suaf.springblog.service.CategoryService;
import hu.suaf.springblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogpost")
public class BlogPostController {

    private BlogPostService blogPostService;
    private CategoryService categoryService;
    private BloggerService bloggerService;
    private CommentService commentService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService, CommentService commentService) {
        this.blogPostService = blogPostService;
        this.commentService = commentService;


    }

    @GetMapping("/{id}")
    public String viewBlogPost(Model model, @PathVariable long id){
        model.addAttribute("poszt", blogPostService.findBlogPostById(id));
        model.addAttribute("komment",new Comment());
        model.addAttribute("valaki",new Blogger());
        return "post";
    }


    @PostMapping("/{id}")
    public String createComment(@PathVariable long id, Comment comment, BindingResult bindingResult){
        comment.setBlogPost(blogPostService.findBlogPostById(id));

        commentService.saveComment(comment);
        return "redirect:/blogpost/" + id;
    }



}
