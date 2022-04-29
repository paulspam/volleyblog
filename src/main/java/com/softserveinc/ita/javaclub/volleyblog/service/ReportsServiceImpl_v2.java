package com.softserveinc.ita.javaclub.volleyblog.service;


import com.softserveinc.ita.javaclub.volleyblog.dto.PlayersWithPostsDto;
import com.softserveinc.ita.javaclub.volleyblog.model.Player;
import com.softserveinc.ita.javaclub.volleyblog.model.Post;
import com.softserveinc.ita.javaclub.volleyblog.repository.PlayerRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.PostLikeRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.PostRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportsServiceImpl_v2 implements ReportsService{
    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final PostLikeRepository postLikeRepository;

    private final PlayerRepository playerRepository;

    public ReportsServiceImpl_v2(UserRepository userRepository,
                                 PostRepository postRepository,
                                 PostLikeRepository postLikeRepository,
                                 PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public List<PlayersWithPostsDto> getTop10PlayersWithPosts() {
        List<PlayersWithPostsDto> playersWithPostsDtoList = new ArrayList<>();
        List<Player> players = playerRepository.findAll();
        for (Player player : players) {
            List<Post> playerPosts = postRepository.findAllByUser(player.getUser());
            if (!playerPosts.isEmpty()) {
                PlayersWithPostsDto playersWithPostsDto = new PlayersWithPostsDto();
                playersWithPostsDto.setPlayerId(player.getPlayerId());
                playersWithPostsDto.setUserId(player.getUser().getUserId());
                playersWithPostsDto.setNickName(player.getNickname());
                Integer allPostsRating = 0;
                Short likeValue = 1;
                for (Post post: playerPosts) {
                    Integer postRating = postLikeRepository
                            .countAllByLikeValueAndPost(likeValue, post);
                    allPostsRating += postRating;
                }
                playersWithPostsDto.setAllPostsRating(allPostsRating);
                playersWithPostsDtoList.add(playersWithPostsDto);
            }
        }
        return playersWithPostsDtoList.stream()
                .sorted((p1, p2) -> p2.getAllPostsRating().compareTo(p1.getAllPostsRating()))
                .collect(Collectors.toList());
    }



}
