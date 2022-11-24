package com.example.forum_backend.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) { this.commentService = commentService;}

    @GetMapping("/comments/{topicId}")
    public ResponseEntity<List<CommentDto>> getCommentsByTopic(@PathVariable Long topicId){

        return new ResponseEntity<>(commentService.getCommentsByTopic(topicId), HttpStatus.OK);
    }

    @PostMapping("/comments/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.addComment(commentDto), HttpStatus.CREATED);
    }

}
