package hu.suaf.springblog.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

   @ManyToOne(fetch = FetchType.LAZY)
   private Blogger author;

   @ManyToOne(fetch = FetchType.LAZY)
   private BlogPost blogPost;



}
