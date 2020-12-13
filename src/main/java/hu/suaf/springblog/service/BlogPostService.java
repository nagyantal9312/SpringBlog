package hu.suaf.springblog.service;

import hu.suaf.springblog.model.*;
import hu.suaf.springblog.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final CategoryRepository categoryRepository;
    private final BloggerRepository bloggerRepository;
    private final CommentRepository commentRepository;
    private final BlogPostReactionRepository blogPostReactionRepository;
    private final CommentReactionRepository commentReactionRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository, CategoryRepository categoryRepository, BloggerRepository bloggerRepository, CommentRepository commentRepository, BlogPostReactionRepository blogPostReactionRepository, CommentReactionRepository commentReactionRepository) {
        this.blogPostRepository = blogPostRepository;
        this.categoryRepository = categoryRepository;
        this.bloggerRepository = bloggerRepository;
        this.commentRepository = commentRepository;
        this.blogPostReactionRepository = blogPostReactionRepository;
        this.commentReactionRepository = commentReactionRepository;
    }

    public BlogPost saveBlogPost(BlogPost b) {
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
        return b;
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



    public void saveBlogPostReaction(long blogPostId, String username, boolean type) {

        //ertekelte e mar a user a posztot ugyanugy korabban mint most
        BlogPostReaction bpr  = blogPostReactionRepository.findByBlogPost_IdAndBlogger_UsernameAndReactionType(blogPostId, username, type);
        //ertekelte e mar a user a posztot a mostani reakcioval ellentetes reakcioval
        BlogPostReaction negalt  = blogPostReactionRepository.findByBlogPost_IdAndBlogger_UsernameAndReactionType(blogPostId, username, !type);

        if(bpr != null) {
            blogPostReactionRepository.deleteById(bpr.getId());
        }else if(negalt != null){
            negalt.setReactionType(type);
            blogPostReactionRepository.save(negalt);
        }else{
            BlogPostReaction blogPostReaction = new BlogPostReaction();
            blogPostReaction.setBlogPost(blogPostRepository.findById(blogPostId).orElse(null));
            blogPostReaction.setBlogger(bloggerRepository.findByUsername(username));
            blogPostReaction.setReactionType(type);
            blogPostReactionRepository.save(blogPostReaction);
        }


    }


    public void saveCommentReaction(long commentId, String username, boolean type) {

        CommentReaction commentReaction = new CommentReaction();
        commentReaction.setComment(commentRepository.findById(commentId).orElse(null));
        commentReaction.setReactionType(type);
        commentReaction.setBlogger(bloggerRepository.findByUsername(username));
        commentReactionRepository.save(commentReaction);

    }

    public int countBlogPostReaction(long blogPostId, boolean type) {
        return blogPostReactionRepository.countBlogPostReactionByBlogPost_IdAndReactionType(blogPostId, type);
    }

    public int countCommentReaction(long commentId, boolean type) {
        return commentReactionRepository.countCommentReactionByComment_IdAndReactionType(commentId, type);

    }


}
