package hu.suaf.springblog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Category {

    @Id
    private String name;


}
