package hu.suaf.springblog.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Blogger extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String photo;

    private Date BirthDate;

    private boolean active;


    @OneToMany(mappedBy = "blogpost_id")
    private List<BlogPost> blogPosts;






}
