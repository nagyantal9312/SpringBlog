package hu.suaf.springblog.service;

import hu.suaf.springblog.model.BlogPost;
import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {

    private BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public void saveBlogPost(BlogPost b){
        blogPostRepository.save(b);
    }

    public List<BlogPost> listBlogPosts() {
        return blogPostRepository.findAll();
    }




}
