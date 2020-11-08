package hu.suaf.springblog.repository;

import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Long> {

    Blogger findByUsername(String username);




    /*@Query("SELECT b from Blogger b join Comment c where b.username = c.createdBy and c.id = :kid ")
    Blogger bloggerOfComment(@Param("kid") long id);
*/


}
