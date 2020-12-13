package hu.suaf.springblog.controller;

import hu.suaf.springblog.model.BlogPost;
import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.service.BlogPostService;
import hu.suaf.springblog.service.BloggerService;
import hu.suaf.springblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/blogger")
public class BloggerController {

    private final BlogPostService blogPostService;
    private final CategoryService categoryService;
    private final BloggerService bloggerService;


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

    /**
     * Posztok listazasa a fo oldalon get keres
     * @param model
     * @param title
     * @return
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

    /**
     * Poszt letrehozo oldalra get keres
     * @param model
     * @param blogPost
     * @return
     */
    @GetMapping("/post-create")
    public String createBlogPostForm(Model model, BlogPost blogPost) {
        //TODO: a blogpost validacio hibaja esetek nem toltodik be
        model.addAttribute("kategoriak", categoryService.listCategories());
        return "post-create";
    }

    /**
     * Uj poszt letrehozasa post keres
     * @param blogPost
     * @param bindingResult
     * @return
     */
    @PostMapping("/post-create")
    public String createBlogPost(@Valid BlogPost blogPost, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "post-create";
        }
        blogPostService.saveBlogPost(blogPost);
        return "redirect:/blogger";
    }

    /**
     * Profil oldal megjelenites get keres
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/profile/{username}")
    public String createProfilePage(@PathVariable String username, Model model) {
        model.addAttribute("ezegyBlogger", bloggerService.findByUsername(username));
        return "profile";
    }

    /**
     * Profil oldal modositasa post keres
     * @param username
     * @param blogger
     * @param result
     * @return
     */
    @PostMapping("/profile/{username}")
    public String editProfile(@PathVariable String username, @Valid Blogger blogger, BindingResult result){
        if(result.hasErrors()){

            return "profile";
        }
        bloggerService.editBlogger(blogger);

        return "redirect:/blogger/profile/" + username;
    }


    /**
     * Profil fenykep modositasa post keres
     * @param username
     * @param image
     * @return
     */
    @PostMapping("/profile/photo/{username}")
    public String editPhoto(@PathVariable String username, @RequestParam("image") MultipartFile image){

        Blogger blogger = bloggerService.findByUsername(username);
        bloggerService.uploadPhoto(blogger,image);

        return "redirect:/blogger/profile/" + username;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }






}
