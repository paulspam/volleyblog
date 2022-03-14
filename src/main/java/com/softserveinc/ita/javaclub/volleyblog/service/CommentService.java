package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.Comment;

import java.util.List;

public interface CommentService {

    Comment findById(Integer id);

    List<Comment> getAllComments();

    Comment saveComment(Comment comment);

    void deleteById(Integer id);
}


