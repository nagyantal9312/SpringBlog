package hu.suaf.springblog.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Blogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String email;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;


    private String name;

    private String photo;

    private Date birthDate;


    @OneToMany(mappedBy = "author")
    private List<BlogPost> blogPosts;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;






}
