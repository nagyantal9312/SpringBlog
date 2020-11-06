package hu.suaf.springblog.service;

import hu.suaf.springblog.model.BlogPost;
import hu.suaf.springblog.model.Category;
import hu.suaf.springblog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BlogPostService {

    private BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
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




}
