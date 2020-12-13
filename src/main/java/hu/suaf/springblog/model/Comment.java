package hu.suaf.springblog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
public class Comment extends AbstractAuditableEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private BlogPost blogPost;

    @Transient
    private String photoHelper;

    @OneToMany(mappedBy = "comment", cascade= CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CommentReaction> commentReactions;

    @Transient
    private int likesNumber;

    @Transient
    private int dislikesNumber;


}
