package hu.suaf.springblog.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Blogger extends AuditableEntity<String> {

    @Id
    private String username;
    private String email;
    private String password;
    private String name;


    private String photo;
    private Date birthDate;

    private boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

   /* @OneToMany()
    private List<BlogPost> blogPosts;*/

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;






}
