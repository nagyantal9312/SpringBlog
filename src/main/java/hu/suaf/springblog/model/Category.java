package hu.suaf.springblog.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<BlogPost> blogPosts;

}
