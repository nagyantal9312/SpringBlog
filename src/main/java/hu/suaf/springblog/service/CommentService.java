package hu.suaf.springblog.service;

import hu.suaf.springblog.model.Comment;
import hu.suaf.springblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {


    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    public Comment findCommentById(long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public void editComment(long commentId, Comment comment) {

        Comment eredeti = commentRepository.findById(commentId).orElse(null);
        eredeti.setText(comment.getText());
        commentRepository.save(eredeti);


    }



}
