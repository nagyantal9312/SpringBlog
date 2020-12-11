package hu.suaf.springblog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class Reaction {


    private boolean reactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blogger blogger;




}
