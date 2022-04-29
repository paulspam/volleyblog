package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.dto.PlayersWithPostsDto;
import com.softserveinc.ita.javaclub.volleyblog.dto.PostDto;
import com.softserveinc.ita.javaclub.volleyblog.model.Player;
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

        return postDto;
    }

    public PlayersWithPostsDto mapToPlayersWithPostsDto(Player player) {
        PlayersWithPostsDto playersWithPostsDto = new PlayersWithPostsDto();
        playersWithPostsDto.setPlayerId(player.getPlayerId());
        playersWithPostsDto.setUserId(player.getUser().getUserId());
        playersWithPostsDto.setNickName(player.getNickname());
        return playersWithPostsDto;
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
