package hu.suaf.springblog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class Category {

    @Id
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<BlogPost> blogPosts;

}
