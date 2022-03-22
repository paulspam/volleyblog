package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.softserveinc.ita.javaclub.volleyblog.model.PostCategory;
import com.softserveinc.ita.javaclub.volleyblog.service.PostCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categories")
public class PostCategoryController {


    private final PostCategoryService postCategoryService;

    public PostCategoryController(PostCategoryService postCategoryService) {
        this.postCategoryService = postCategoryService;
    }

    @GetMapping()
    public ResponseEntity<List<PostCategory>> findAll() {
        List<PostCategory> allPostCategory = postCategoryService.findAll();
        return ResponseEntity.ok(allPostCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostCategory> findById(@PathVariable int id) {
        PostCategory postCategory = postCategoryService.findById(id);
        if (postCategory == null) {
            return new ResponseEntity("IN PostCategoryController.findById - No postCategory with postCategoryId = " + id, HttpStatus.NOT_ACCEPTABLE);
        } else return ResponseEntity.ok(postCategory);
    }

    @PostMapping()
    public ResponseEntity<PostCategory> save(@RequestBody PostCategory postCategory) {
        if ((postCategory.getCategoryId() != null) && (postCategory.getCategoryId() !=0)) {
            return new ResponseEntity("IN PostCategoryController.save - Redundant parameter: postCategoryId must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        PostCategory newPostCategory = null;
        try {
            newPostCategory = postCategoryService.save(postCategory);
        } catch (AccessDeniedException e) {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (newPostCategory != null) {
            return ResponseEntity.ok(newPostCategory);
        } else {
            return new ResponseEntity("PostCategory not saved", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping()
    public ResponseEntity<PostCategory> update(@RequestBody PostCategory postCategory) {
        if ((postCategory.getCategoryId() == null) || (postCategory.getCategoryId() ==0)) {
            return new ResponseEntity("IN PostCategoryController.update - Missing parameter: postCategoryId must be not null", HttpStatus.NOT_ACCEPTABLE);
        }
        PostCategory updatedPostCategory = null;
        try {
            updatedPostCategory = postCategoryService.update(postCategory);
        } catch (AccessDeniedException e) {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (updatedPostCategory != null) {
            return ResponseEntity.ok(updatedPostCategory);
        } else {
            return new ResponseEntity("PostCategory not updated", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        PostCategory postCategory = postCategoryService.findById(id);
        if (postCategory == null) {
            return new ResponseEntity("IN PostCategoryController.deleteById - No postCategory with postCategoryId = " + id, HttpStatus.NOT_ACCEPTABLE);
        } else {
            try {
                postCategoryService.deleteById(id);
            } catch (AccessDeniedException e) {
                return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok("IN PostCategoryController.deleteById - PostCategory with ID = " + id + " was deleted");
        }
    }
}
