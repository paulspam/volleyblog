package com.softserveinc.ita.javaclub.volleyblog.repository;

import com.softserveinc.ita.javaclub.volleyblog.model.Post;
import com.softserveinc.ita.javaclub.volleyblog.model.PostCategory;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByUser(User user);
    List<Post> findAllByPostCategory(PostCategory postCategory);

    Post findPostByPostId(Integer postId);

}
