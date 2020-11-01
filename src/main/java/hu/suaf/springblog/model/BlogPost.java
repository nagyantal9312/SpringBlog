package hu.suaf.springblog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class BlogPost{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String text;


    @ManyToOne(fetch = FetchType.LAZY)
    private Blogger author;


    @ManyToMany
    private List<Category> categories;

    @OneToMany(mappedBy = "blogPost")
    private List<Comment> comments;




}
