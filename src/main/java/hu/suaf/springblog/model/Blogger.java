package hu.suaf.springblog.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Blogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String photo;

    private Date birthDate;

    //private boolean active;


    @OneToMany(mappedBy = "id")
    private List<BlogPost> blogPosts;

    @OneToMany(mappedBy = "id")
    private List<Comment> comments;






}
