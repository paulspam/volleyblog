package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.dto.PostDto;
import com.softserveinc.ita.javaclub.volleyblog.model.Post;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.softserveinc.ita.javaclub.volleyblog.security.constants.Permissions.MANAGE_POSTS;

public interface PostService {

    Post findById(Integer id);

    List<Post> findAll();

    @PreAuthorize(MANAGE_POSTS)
    Post save(Post post);

    @PreAuthorize("#post.user.userName == authentication.name")
    Post update(Post post);

    @PreAuthorize(MANAGE_POSTS)
    void deleteById(Integer id);

    List<Post> findAllByUser(User user);

    PostDto getPostWithComments(Integer postId);
}
