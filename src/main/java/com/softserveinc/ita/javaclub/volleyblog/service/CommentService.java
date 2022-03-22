package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.Comment;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.softserveinc.ita.javaclub.volleyblog.security.constants.Permissions.MANAGE_COMMENTS;

public interface CommentService {

    Comment findById(Integer id);

    List<Comment> findAll();

    @PreAuthorize(MANAGE_COMMENTS)
    Comment save(Comment comment);

    @PreAuthorize(MANAGE_COMMENTS)
    void deleteById(Integer id);
}


