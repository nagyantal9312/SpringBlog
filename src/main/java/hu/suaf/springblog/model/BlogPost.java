package hu.suaf.springblog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String text;


    @ManyToOne(fetch = FetchType.LAZY)
    private Blogger author;


    @OneToMany
    private List<Category> categories;




}
