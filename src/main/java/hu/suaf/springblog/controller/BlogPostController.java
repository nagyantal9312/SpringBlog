package hu.suaf.springblog.controller;

import hu.suaf.springblog.model.BlogPost;
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
    public BlogPostController(BlogPostService blogPostService, CommentService commentService, CategoryService categoryService, BloggerService bloggerService) {
        this.blogPostService = blogPostService;
        this.commentService = commentService;
        this.categoryService = categoryService;
        this.bloggerService = bloggerService;

    }

    @GetMapping("/{blogPostId}")
    public String viewBlogPost(Model model, @PathVariable long blogPostId){

        BlogPost blogPost = blogPostService.findBlogPostById(blogPostId);
        for(Comment item : blogPost.getComments()){
            item.setLikesNumber(blogPostService.countCommentReaction(item.getId(),true));
            item.setDislikesNumber(blogPostService.countCommentReaction(item.getId(),false));
        }
        model.addAttribute("poszt", blogPost);
        model.addAttribute("komment",new Comment());
        model.addAttribute("likeSzam", blogPostService.countBlogPostReaction(blogPostId,true));
        model.addAttribute("dislikeSzam", blogPostService.countBlogPostReaction(blogPostId,false));

        return "post";
    }


    @PostMapping("/{blogPostId}")
    public String createComment(@PathVariable long blogPostId, Comment comment, BindingResult bindingResult){
        comment.setBlogPost(blogPostService.findBlogPostById(blogPostId));

        commentService.saveComment(comment);
        return "redirect:/blogpost/" + blogPostId;
    }


    @GetMapping("/edit/{blogPostId}")
    public String editBlogPostForm(@PathVariable long blogPostId, Model model){
        BlogPost blogPost = blogPostService.findBlogPostById(blogPostId);
        model.addAttribute("kategoriak", categoryService.listCategories());
        model.addAttribute("blogPost",blogPost);
        return "post-create";
    }

    //NEM MUKODIK
    @PostMapping("/edit/{blogPostId}")
    public String editBlogPost(@PathVariable long blogPostId, Model model, BlogPost blogPost){
        blogPostService.saveBlogPost(blogPost);
        return "redirect:/blogpost/" + blogPostId;
    }


    @GetMapping("/delete/{blogPostId}")
    public String deleteBlogPost(@PathVariable long blogPostId){
        blogPostService.deleteBlogPost(blogPostId);
        return "redirect:/blogger";
    }

    @GetMapping("/comment/delete/{commentId}")
    public String deleteComment(@PathVariable long commentId){
        long idForRedirecting = commentService.findCommentById(commentId).getBlogPost().getId();
        commentService.deleteComment(commentId);
        return "redirect:/blogpost/" + idForRedirecting;
    }

    @GetMapping("/comment/edit/{commentId}")
    public String editCommentForm(@PathVariable long commentId, Model model){
        Comment comment = commentService.findCommentById(commentId);
        model.addAttribute("komment",comment);
        return "comment-edit";
    }

    @PostMapping("/comment/edit/{commentId}")
    public String editComment(@PathVariable long commentId, Model model, Comment comment){
        comment.setBlogPost(commentService.findCommentById(commentId).getBlogPost());
        commentService.saveComment(comment);
        return "redirect:/blogpost/" + comment.getBlogPost().getId();
    }


    @GetMapping("/{blogPostId}/{username}/{type}")
    public String reactToBlogPost(@PathVariable long blogPostId, @PathVariable String username, @PathVariable boolean type, Model model){

        blogPostService.saveBlogPostReaction(blogPostId,username,type);
        return "redirect:/blogpost/" + blogPostId;
    }

    @GetMapping("/comment/{commentId}/{username}/{type}")
    public String reactToComment(@PathVariable long commentId, @PathVariable String username, @PathVariable boolean type, Model model){

        long idForRedirecting = commentService.findCommentById(commentId).getBlogPost().getId();
        blogPostService.saveCommentReaction(commentId,username,type);
        return "redirect:/blogpost/" + idForRedirecting;
    }



}
