package com.softserveinc.ita.javaclub.volleyblog.dto;


import com.softserveinc.ita.javaclub.volleyblog.model.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostDto {

    private Integer postId;

    private String title;

    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private User user;

    private List<Comment> comments;

    private PostCategory postCategory;

    private PostStatus postStatus;

    private List<Tag> tags;
}
