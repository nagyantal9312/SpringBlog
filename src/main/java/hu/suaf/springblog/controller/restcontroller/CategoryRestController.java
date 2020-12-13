package hu.suaf.springblog.controller.restcontroller;

import hu.suaf.springblog.model.Category;
import hu.suaf.springblog.model.Comment;
import hu.suaf.springblog.service.BlogPostService;
import hu.suaf.springblog.service.BloggerService;
import hu.suaf.springblog.service.CategoryService;
import hu.suaf.springblog.service.CommentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Spliterators;

@RestController
@RequestMapping(path = "/api/category", produces = "application/json")
@CrossOrigin("*")
public class CategoryRestController {

    private BlogPostService blogPostService;
    private CategoryService categoryService;
    private BloggerService bloggerService;
    private CommentService commentService;


    public CategoryRestController(BlogPostService blogPostService, CommentService commentService, CategoryService categoryService) {
        this.blogPostService = blogPostService;
        this.commentService = commentService;
        this.categoryService = categoryService;

    }

    /**
     * Kategoriak listazasa
     * @return
     */
    @GetMapping
    public Iterable<Category> listCategories(){
        return categoryService.listCategories();
    }

    /**
     * Kategoria torlese nev alapjan
     * @param name
     */
    @DeleteMapping("/delete/{name}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteCategory(@PathVariable String name){
        categoryService.deleteCategory(name);
    }


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<Category> addCategory(@RequestBody Category category){
        //return categoryService.saveCategory(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(category));
    }




}
