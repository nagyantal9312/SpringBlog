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


    /**
     * Posztok cimeben kereses GET keressel
     * @param model model
     * @param title a szoveg amit a cimben keresunk
     * @return fooldal, amin a keresett posztok listazodnak
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
     * Poszt letrehozasara szolgalo oldal feltoltese adatokkal
     * @param model model
     * @param blogPost a letrehozando poszt objektum
     * @return a posztok letrehozasara szolgalo oldal
     */
    @GetMapping("/post-create")
    public String createBlogPostForm(Model model, BlogPost blogPost) {
        //TODO: validacios hiba eseten nem toltodik be ujra a kategoriak listaja
        model.addAttribute("kategoriak", categoryService.listCategories());
        return "post-create";
    }

    /**
     * Uj poszt letrehozasa POST keressel
     * @param blogPost az uj BlogPost
     * @param bindingResult bindingresult
     * @return ha sikeres a letrehozas akkor a fooldal, egyeb esetben a poszt letrehozo oldal
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
     * Profil oldal megjelenites GET keressel
     * @param username a felhasznalo akie a profil oldal
     * @param model model
     * @return a felhasznalo profil oldala
     */
    @GetMapping("/profile/{username}")
    public String createProfilePage(@PathVariable String username, Model model) {
        model.addAttribute("ezegyBlogger", bloggerService.findByUsername(username));
        return "profile";
    }

    /**
     * Profil oldal modositasa POST keressel
     * @param username a modositando felhasznalo felhasznaloneve
     * @param blogger a modositas utani objektum
     * @param result result
     * @return a felhasznalo profil oldala
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
     * Profil fenykep modositasa POST keressel
     * @param username a felhasznalo akinek a fenykepet modositani akarjuk
     * @param image a kep amivel modositani akarunk
     * @return a felhasznalo profil oldala
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
