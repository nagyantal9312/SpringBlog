package hu.suaf.springblog.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class BlogPost extends AbstractAuditableEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    @NotBlank
    private String text;



    @ManyToMany
    @JsonManagedReference
    private List<Category> categories;


    @OneToMany(mappedBy = "blogPost", cascade= CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> comments;

    @Transient
    private String categoryHelper;

    @OneToMany(mappedBy = "blogPost", cascade= CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BlogPostReaction> blogPostReactions;




}
