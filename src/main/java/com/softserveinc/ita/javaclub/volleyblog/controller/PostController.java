package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.softserveinc.ita.javaclub.volleyblog.model.Post;
import com.softserveinc.ita.javaclub.volleyblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public ResponseEntity<List<Post>> findAll() {
        List<Post> allPosts = postService.findAll();
        return ResponseEntity.ok(allPosts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable int id) {
        Post post = postService.findById(id);
        if (post == null) {
            return new ResponseEntity("No post with postId = " + id, HttpStatus.NOT_ACCEPTABLE);
        } else return ResponseEntity.ok(post);
    }

    @PostMapping()
    public ResponseEntity<Post> saveNewPost(@RequestBody Post post) {
        if ((post.getPostId() != null) && (post.getPostId() !=0)) {
            return new ResponseEntity("Redundant parameter: postId must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        Post newPost = postService.savePost(post);
        return ResponseEntity.ok(newPost);
    }

    @PutMapping()
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        if ((post.getPostId() == null) || (post.getPostId() ==0)) {
            return new ResponseEntity("Missing parameter: postId must be not null", HttpStatus.NOT_ACCEPTABLE);
        }
        Post newPost = postService.savePost(post);
        return ResponseEntity.ok(newPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
        Post post = postService.findById(id);
        if (post == null) {
            return new ResponseEntity("No post with postId = " + id, HttpStatus.NOT_ACCEPTABLE);
        } else {
            postService.deleteById(id);
            return ResponseEntity.ok("Post with ID = " + id + " was deleted");
        }
    }
/*
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Post>> findAllByAuthorId(@PathVariable Integer authorId) {
        List<Post> findAllByAuthorId = postService.findAllByUser(User);
        return ResponseEntity.ok(findAllByAuthorId);
    }*/


}
