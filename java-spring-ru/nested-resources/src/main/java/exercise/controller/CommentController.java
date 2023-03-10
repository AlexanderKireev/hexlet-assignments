package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getComments(@PathVariable(name = "postId") long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable(name = "postId") long postId, @PathVariable(name = "commentId") long commentId) {
        return commentRepository.findByPostIdAndId(postId, commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    @PostMapping(path = "/{postId}/comments")
    public Comment createComment(@PathVariable long postId, @RequestBody Comment comment) {
        Post p = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        comment.setPost(p);
        return commentRepository.save(comment);
    }


    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable(name = "postId") long postId,
                                 @PathVariable(name = "commentId") long commentId,
                                 @RequestBody Comment comment) {

        Comment newComment = commentRepository.findByPostIdAndId(postId, commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        System.out.println("newComment" + newComment);

//        Post p = postRepository.findById(postId)
//                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));


        newComment.setId(commentId);
        newComment.setContent(comment.getContent());

//        newComment.setPost(comment.getPost());
        return commentRepository.save(newComment);
    }



    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable(name = "postId") long postId,
                              @PathVariable(name = "commentId") long commentId) {
        Comment comment = commentRepository.findByPostIdAndId(postId, commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }
    // END
}
