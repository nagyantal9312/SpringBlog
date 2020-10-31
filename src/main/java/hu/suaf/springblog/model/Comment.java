package hu.suaf.springblog.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Comment {

    @Id
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blogger author;




}
