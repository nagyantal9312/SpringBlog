package hu.suaf.springblog.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class BlogPost extends AuditableEntity<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String text;



    @ManyToMany
    private List<Category> categories;


    @OneToMany(mappedBy = "blogPost", cascade= CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @Transient
    private String categoryHelper;

    @OneToMany(mappedBy = "blogPost", cascade= CascadeType.ALL, orphanRemoval = true)
    private List<BlogPostReaction> blogPostReactions;




}
