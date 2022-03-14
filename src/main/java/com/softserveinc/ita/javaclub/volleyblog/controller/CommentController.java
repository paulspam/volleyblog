package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.softserveinc.ita.javaclub.volleyblog.model.Comment;
import com.softserveinc.ita.javaclub.volleyblog.service.CommentService;
import com.softserveinc.ita.javaclub.volleyblog.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comments")
public class CommentController {


    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping()
    public ResponseEntity<List<Comment>> findAll() {
        List<Comment> allComments = commentService.getAllComments();
        return ResponseEntity.ok(allComments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable int id) {
        Comment comment = commentService.findById(id);
        if (comment == null) {
            return new ResponseEntity("No comment with commentId = " + id, HttpStatus.NOT_ACCEPTABLE);
        } else return ResponseEntity.ok(comment);
    }

    @PostMapping()
    public ResponseEntity<Comment> saveNewComment(@RequestBody Comment comment) {
        if ((comment.getCommentId() != null) && (comment.getCommentId() !=0)) {
            return new ResponseEntity("Redundant parameter: commentId must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        Comment newComment = commentService.saveComment(comment);
        return ResponseEntity.ok(newComment);
    }

    @PutMapping()
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
        if ((comment.getCommentId() == null) || (comment.getCommentId() ==0)) {
            return new ResponseEntity("Missing parameter: commentId must be not null", HttpStatus.NOT_ACCEPTABLE);
        }
        Comment newComment = commentService.saveComment(comment);
        return ResponseEntity.ok(newComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable int id) {
        Comment comment = commentService.findById(id);
        if (comment == null) {
            return new ResponseEntity("No comment with commentId = " + id, HttpStatus.NOT_ACCEPTABLE);
        } else {
            commentService.deleteById(id);
            return ResponseEntity.ok("Comment with ID = " + id + " was deleted");
        }
    }
}
