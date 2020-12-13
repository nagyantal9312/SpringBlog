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
@RequestMapping(value = "/blogpost")
public class BlogPostController {

    private final BlogPostService blogPostService;
    private final CategoryService categoryService;
    private final BloggerService bloggerService;
    private final CommentService commentService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService, CommentService commentService, CategoryService categoryService, BloggerService bloggerService) {
        this.blogPostService = blogPostService;
        this.commentService = commentService;
        this.categoryService = categoryService;
        this.bloggerService = bloggerService;

    }

    /**
     * Poszt oldal feltoltese adatokkal
     * @param model model
     * @param blogPostId annak a posztnak az id-je, amelyiknek az oldalat fel akarjuk tolteni adatokkal
     * @return a poszt oldala
     */
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

    /**
     * Komment letrehozasa
     * @param blogPostId a kommentelni kivant poszt id-je
     * @param comment a letrehozando komment
     * @param bindingResult bindingresult
     * @return a letrehozni kivant kommenthez tartozo poszt oldala
     */
    @PostMapping("/{blogPostId}")
    public String createComment(@PathVariable long blogPostId, Comment comment, BindingResult bindingResult){
        comment.setBlogPost(blogPostService.findBlogPostById(blogPostId));

        commentService.saveComment(comment);
        return "redirect:/blogpost/" + blogPostId;
    }

    /**
     * A posztot modosito oldal feltoltese a modositas elotti objektummal.
     * @param blogPostId a modositando poszt id-je
     * @param model model
     * @return a poszt modositasara szolgalo oldal
     */
    @GetMapping("/edit/{blogPostId}")
    public String editBlogPostForm(@PathVariable long blogPostId, Model model){
        BlogPost blogPost = blogPostService.findBlogPostById(blogPostId);
        model.addAttribute("kategoriak", categoryService.listCategories());
        model.addAttribute("blogPost",blogPost);
        return "post-create";
    }

    /**
     * Poszt modositasa
     * @param blogPostId a modositando poszt id-je
     * @param model model
     * @param blogPost az uj poszt objektum
     * @return a modositando poszt oldala
     */
    @PostMapping("/edit/{blogPostId}")
    public String editBlogPost(@PathVariable long blogPostId, Model model, BlogPost blogPost){
        blogPostService.editBlogPost(blogPostId, blogPost);
        return "redirect:/blogpost/" + blogPostId;
    }

    /**
     * Poszt torlese
     * @param blogPostId a torolni kivant poszt id-je
     * @return a fooldal
     */
    @GetMapping("/delete/{blogPostId}")
    public String deleteBlogPost(@PathVariable long blogPostId){
        blogPostService.deleteBlogPost(blogPostId);
        return "redirect:/blogger";
    }

    /**
     * Komment torlese
     * @param commentId a torolni kivant komment id-je
     * @return a torolni kivant kommenthez tartozo poszt oldala
     */
    @GetMapping("/comment/delete/{commentId}")
    public String deleteComment(@PathVariable long commentId){
        long idForRedirecting = commentService.findCommentById(commentId).getBlogPost().getId();
        commentService.deleteComment(commentId);
        return "redirect:/blogpost/" + idForRedirecting;
    }

    /**
     * Komment modosito form feltoltese a modositando komment adataival
     * @param commentId a modositando komment id-je
     * @param model model
     * @return a komment szerkesztesere szolgalo oldal
     */
    @GetMapping("/comment/edit/{commentId}")
    public String editCommentForm(@PathVariable long commentId, Model model){
        Comment comment = commentService.findCommentById(commentId);
        model.addAttribute("komment",comment);
        return "comment-edit";
    }

    /**
     * Komment modositasa
     * @param commentId a modositando komment id-je
     * @param model model
     * @param comment az uj komment objektum
     * @return a kommenthez tartozo poszt oldala
     */
    @PostMapping("/comment/edit/{commentId}")
    public String editComment(@PathVariable long commentId, Model model, Comment comment){
        long idForRedirecting = commentService.findCommentById(commentId).getBlogPost().getId();
        commentService.editComment(commentId, comment);
        return "redirect:/blogpost/" + idForRedirecting;
    }

    /**
     * Poszt likeolasa / dislikeolasa
     * @param blogPostId az ertekelni kivant poszt id-ja
     * @param username az ertekelo felhasznalo felhasznaloneve
     * @param type true:like, false:dislike
     * @param model model
     * @return az ertekelni kivant poszt oldala
     */
    @GetMapping("/{blogPostId}/{username}/{type}")
    public String reactToBlogPost(@PathVariable long blogPostId, @PathVariable String username, @PathVariable boolean type, Model model){

        blogPostService.saveBlogPostReaction(blogPostId,username,type);
        return "redirect:/blogpost/" + blogPostId;
    }

    /**
     * Komment likeolasa / dislikeolasa
     * @param commentId az ertekelni kivant komment id-ja
     * @param username az ertekelo felhasznalo felhasznaloneve
     * @param type true:like, false:dislike
     * @param model model
     * @return az ertekelni kivant kommenthez tartozo poszt oldala
     */
    @GetMapping("/comment/{commentId}/{username}/{type}")
    public String reactToComment(@PathVariable long commentId, @PathVariable String username, @PathVariable boolean type, Model model){

        long idForRedirecting = commentService.findCommentById(commentId).getBlogPost().getId();
        blogPostService.saveCommentReaction(commentId,username,type);
        return "redirect:/blogpost/" + idForRedirecting;
    }



}
