package hu.suaf.springblog.service;

import hu.suaf.springblog.model.BlogPost;
import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.model.Category;
import hu.suaf.springblog.model.Comment;
import hu.suaf.springblog.repository.BlogPostRepository;
import hu.suaf.springblog.repository.BloggerRepository;
import hu.suaf.springblog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BlogPostService {

    private BlogPostRepository blogPostRepository;
    private CategoryRepository categoryRepository;
    private BloggerRepository bloggerRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository, CategoryRepository categoryRepository, BloggerRepository bloggerRepository) {
        this.blogPostRepository = blogPostRepository;
        this.categoryRepository = categoryRepository;
        this.bloggerRepository = bloggerRepository;
    }

    public void saveBlogPost(BlogPost b){
        List<Category> c = new ArrayList<>();
        List<String> k = Arrays.asList(b.getCategoryHelper().split(","));
        for(String item : k) {
            Category category = new Category();
            category.setName(item);
            c.add(category);
        }
        b.setCategories(c);
        b.setCategoryHelper(null);
        blogPostRepository.save(b);
    }



    public List<BlogPost> listBlogPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost findBlogPostById(long id) {

        System.out.println(blogPostRepository.findById(id).toString() + " ALALA " + id);
        BlogPost blogPost = blogPostRepository.findById(id).orElse(null);
        /*for(Comment item : blogPost.getComments()){
            item.setCommentCreator(bloggerRepository.bloggerOfComment(id));
            System.out.println(item.getCommentCreator().getUsername());
        }
*/
        return blogPost;
    }




}
