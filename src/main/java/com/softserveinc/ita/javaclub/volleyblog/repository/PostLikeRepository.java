package com.softserveinc.ita.javaclub.volleyblog.repository;

import com.softserveinc.ita.javaclub.volleyblog.model.Post;
import com.softserveinc.ita.javaclub.volleyblog.model.PostLike;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {
    List<PostLike> findAllByPostAndUser(Post post, User user);
    Integer countAllByLikeValueAndPostAndUser(Short likeValue, Post post, User user);
    Integer countAllByLikeValueAndPost(Short likeValue, Post post);
}
