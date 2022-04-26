package com.softserveinc.ita.javaclub.volleyblog.dto;

import com.softserveinc.ita.javaclub.volleyblog.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopPlayersWithPosts {
    private Integer playerId;
    private Integer userId;
    private String nickName;
//    private Post post;
    private Integer allPostsRating;

}
