package hu.suaf.springblog.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<B> implements Serializable {

    @CreatedDate
    @Column(updatable = false)
    private Date createdDate;

    @CreatedBy
    @Column(updatable = false)
    private B createdBy;

    @LastModifiedDate
    private Date lastModifiedDate;

    @LastModifiedBy
    private B lastModifiedBy;





}
