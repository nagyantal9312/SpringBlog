package hu.suaf.springblog.service;

import hu.suaf.springblog.repository.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloggerService {

    private BloggerRepository bloggerRepository;

    @Autowired
    public BloggerService(BloggerRepository bloggerRepository) {
        this.bloggerRepository = bloggerRepository;
    }



}
