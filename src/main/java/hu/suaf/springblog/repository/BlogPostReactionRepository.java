package hu.suaf.springblog.repository;

import hu.suaf.springblog.model.BlogPostReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostReactionRepository extends JpaRepository<BlogPostReaction,Long> {

    int countBlogPostReactionByBlogPost_IdAndReactionType(long blogPostId,boolean type);
}
