package hu.suaf.springblog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;


    @ManyToOne
    @JoinColumn(name = "blogger_id")
    private Blogger author;


    @OneToMany
    private List<Category> categories;




}
