package hu.suaf.springblog.controller.restcontroller;

import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.service.BlogPostService;
import hu.suaf.springblog.service.BloggerService;
import hu.suaf.springblog.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/blogger", produces = "application/json")
@CrossOrigin("*")
public class BloggerRestController {


    private final BlogPostService blogPostService;
    private final CategoryService categoryService;
    private final BloggerService bloggerService;


    public BloggerRestController(BlogPostService blogPostService, CategoryService categoryService, BloggerService bloggerService) {
        this.blogPostService = blogPostService;
        this.categoryService = categoryService;
        this.bloggerService = bloggerService;
    }


    /**
     * Visszaadja a parameterben kapott username alapjan a bloggert
     * @param username felhasznalonev
     * @return a keresett blogger
     */
    @GetMapping("/{username}")
    public ResponseEntity<Blogger> getProfile(@PathVariable String username){

        Blogger blogger = bloggerService.findByUsername(username);
        if(blogger == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(blogger,HttpStatus.OK);
    }


    /**
     * Blogger torlese felhasznalonev alapjan
     * @param username
     */
    @DeleteMapping("/delete/{username}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteProfile(@PathVariable String username){
        bloggerService.deleteBlogger(username);
    }


    /**
     * Letiltja a felhasznalot, ha mar le van tiltva akkor pedig leveszi rola a tiltast
     * @param username
     * @return
     */
    @PutMapping("/ban/{username}")
    public ResponseEntity<Blogger> banBlogger(@PathVariable String username){
        Blogger blogger = bloggerService.findByUsername(username);
        if(blogger == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(bloggerService.banBlogger(blogger));
    }












}
