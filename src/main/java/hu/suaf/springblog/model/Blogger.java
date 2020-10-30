package hu.suaf.springblog.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Blogger extends AuditableEntity{

    @Id
    private long id;

    private String name;

    private String password;

    private String photo;

    private Date BirthDate;

    private boolean active;






}
