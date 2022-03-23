package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.dto.PostDto;
import com.softserveinc.ita.javaclub.volleyblog.model.Post;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    public PostDto mapToPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setCreatedDate(post.getCreatedDate());
        postDto.setModifiedDate(post.getModifiedDate());
        postDto.setUser(post.getUser());
        postDto.setPostCategory(post.getPostCategory());
        postDto.setPostStatus(post.getPostStatus());
        postDto.setTags(post.getTags());

        return postDto;
    }

}

/*

    private String title;

    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private User user;

    private List<Comment> comments;

    private PostCategory postCategory;

    private PostStatus postStatus;*/
