package hu.suaf.springblog.service;

import hu.suaf.springblog.model.BlogPost;
import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.model.Category;
import hu.suaf.springblog.model.Comment;
import hu.suaf.springblog.repository.BlogPostRepository;
import hu.suaf.springblog.repository.BloggerRepository;
import hu.suaf.springblog.repository.CategoryRepository;
import hu.suaf.springblog.repository.CommentRepository;
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
    private CommentRepository commentRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository, CategoryRepository categoryRepository, BloggerRepository bloggerRepository, CommentRepository commentRepository) {
        this.blogPostRepository = blogPostRepository;
        this.categoryRepository = categoryRepository;
        this.bloggerRepository = bloggerRepository;
        this.commentRepository = commentRepository;
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
        //biztositja hogy a cim elso betuje nagybetus legyen
        b.setTitle(b.getTitle().substring(0,1).toUpperCase() + b.getTitle().substring(1));
        blogPostRepository.save(b);
    }



    public List<BlogPost> listBlogPosts() {
        return blogPostRepository.findAllByOrderByLastModifiedDateDesc();
    }

    public BlogPost findBlogPostById(long id) {

        BlogPost blogPost = blogPostRepository.findById(id).orElse(null);
        for(Comment item : blogPost.getComments()) {
            Blogger blogger = bloggerRepository.findByUsername(item.getCreatedBy());
            item.setPhotoHelper(blogger.getPhoto());
        }

        return blogPost;
    }

    public void deleteBlogPost(long id) {
        blogPostRepository.deleteById(id);

    }

    public List<BlogPost> searchBlogPostByTitle(String title) {
        return blogPostRepository.findAllByTitleContainingOrderByLastModifiedDateDesc(title);
    }


}
