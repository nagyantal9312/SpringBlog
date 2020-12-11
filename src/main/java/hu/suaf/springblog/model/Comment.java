package hu.suaf.springblog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Comment extends AuditableEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;


    @ManyToOne(fetch = FetchType.LAZY)
    private BlogPost blogPost;

    @Transient
    private String photoHelper;

    @OneToMany(mappedBy = "comment", cascade= CascadeType.ALL, orphanRemoval = true)
    private List<CommentReaction> commentReactions;

    @Transient
    private int likesNumber;

    @Transient
    private int dislikesNumber;


}
