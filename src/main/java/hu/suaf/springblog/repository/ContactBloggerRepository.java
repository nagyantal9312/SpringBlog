package hu.suaf.springblog.repository;

import hu.suaf.springblog.model.ContactBlogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactBloggerRepository extends JpaRepository<ContactBlogger, Long> {
}
