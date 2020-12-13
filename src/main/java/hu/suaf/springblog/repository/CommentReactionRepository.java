package hu.suaf.springblog.repository;

import hu.suaf.springblog.model.BlogPostReaction;
import hu.suaf.springblog.model.CommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReactionRepository extends JpaRepository<CommentReaction,Long> {

    int countCommentReactionByComment_IdAndReactionType(long commentId, boolean type);
    int countDistinctByComment_IdAndReactionType(long commentId, boolean type);
    CommentReaction findByComment_IdAndBlogger_UsernameAndReactionType(long commentId, String username, boolean type);

}
