package com.example.forum_backend.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) { this.commentService = commentService;}

    @GetMapping("/comments/{topicId}")
    public Optional<Comment> getCommentsByTopic(@PathVariable Long topicId){
        return commentService.getCommentsByTopic(topicId);
    }

    @PostMapping("/comments/addNew")
    @ResponseStatus(HttpStatus.CREATED)
    public void postComment(@RequestBody Comment comment) { commentService.postComment(comment);}

}
