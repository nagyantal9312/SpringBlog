package hu.suaf.springblog.controller.restcontroller;


import hu.suaf.springblog.model.Comment;
import hu.suaf.springblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/comment", produces = "application/json")
@CrossOrigin("*")
public class CommentRestController {


    private final CommentService commentService;

    public CommentRestController(CommentService commentService){
        this.commentService = commentService;
    }


    /**
     * Komment keresese id alapjan
     * @param commentId
     * @return
     */
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable long commentId){
        Comment comment = commentService.findCommentById(commentId);
        if(comment == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }

    /**
     * Komment torlese id alapjan
     * @param commentId
     */
    @DeleteMapping("/delete/{commentId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable long commentId){
        commentService.deleteComment(commentId);
    }


}
