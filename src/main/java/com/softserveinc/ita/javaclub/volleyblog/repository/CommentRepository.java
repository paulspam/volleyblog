package com.softserveinc.ita.javaclub.volleyblog.repository;

import com.softserveinc.ita.javaclub.volleyblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
