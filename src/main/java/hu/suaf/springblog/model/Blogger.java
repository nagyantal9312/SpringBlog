package hu.suaf.springblog.model;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Blogger extends AuditableEntity<String> implements UserDetails {

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




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


}
