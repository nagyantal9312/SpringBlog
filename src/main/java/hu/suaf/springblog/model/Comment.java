package hu.suaf.springblog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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


}
