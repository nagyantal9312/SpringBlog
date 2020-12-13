package hu.suaf.springblog.controller.restcontroller;

import hu.suaf.springblog.model.BlogPost;
import hu.suaf.springblog.service.BlogPostService;
import hu.suaf.springblog.service.CategoryService;
import hu.suaf.springblog.service.CommentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/blogpost", produces = "application/json")
@CrossOrigin("*")
public class BlogPostRestController {

    private final BlogPostService blogPostService;
    private final CategoryService categoryService;
    private final CommentService commentService;


    public BlogPostRestController(BlogPostService blogPostService, CommentService commentService, CategoryService categoryService) {
        this.blogPostService = blogPostService;
        this.commentService = commentService;
        this.categoryService = categoryService;

    }

    /**
     * Poszt keresese id alapjan
     * @param blogPostId
     * @return
     */
    @GetMapping("/{blogPostId}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable long blogPostId){
        BlogPost blogPost = blogPostService.findBlogPostById(blogPostId);
        if(blogPost == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(blogPost,HttpStatus.OK);
    }




    /**
     * Poszt torlese id alapjan
     * @param blogPostId
     */
    @DeleteMapping("/delete/{blogPostId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteBlogPost(@PathVariable long blogPostId){
        blogPostService.deleteBlogPost(blogPostId);
    }



    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<BlogPost> addBlogPost(@RequestBody BlogPost blogPost){

        return ResponseEntity.status(HttpStatus.CREATED).body(blogPostService.saveBlogPost(blogPost));
    }




}
