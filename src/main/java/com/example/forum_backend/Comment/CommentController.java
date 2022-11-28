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

    @GetMapping("/topics/{topicId}/comments")
    public List<CommentDto> getCommentsByTopic(@PathVariable(value = "topicId") Long topicId){

        return commentService.getCommentsByTopic(topicId);
    }

    @PostMapping("/topics/{topicId}/comments")
    public ResponseEntity<CommentDto> addComment(
            @PathVariable(value = "topicId") Long topicId,
            @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.addComment(topicId, commentDto), HttpStatus.CREATED);
    }

    @PutMapping("/topics/{topicId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable long commentId){
        CommentDto response = commentService.updateComment(commentDto, commentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
