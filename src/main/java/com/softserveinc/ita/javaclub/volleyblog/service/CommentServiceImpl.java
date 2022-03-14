package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.Comment;
import com.softserveinc.ita.javaclub.volleyblog.model.Post;
import com.softserveinc.ita.javaclub.volleyblog.repository.CommentRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment findById(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }
}
