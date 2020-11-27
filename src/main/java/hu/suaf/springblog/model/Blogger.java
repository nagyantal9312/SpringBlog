package hu.suaf.springblog.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Blogger extends AuditableEntity<String> implements UserDetails {

    @Id
    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private String password;

    @NotBlank
    private String name;

    @Column(columnDefinition = "LONGTEXT")
    private String photo;

    @PastOrPresent
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthDate;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Role> roles = this.getRoles();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
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

    @Override
    public boolean isEnabled() {
        return true;
    }


}
