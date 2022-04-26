package com.softserveinc.ita.javaclub.volleyblog.service;


import com.softserveinc.ita.javaclub.volleyblog.dto.TopPlayersWithPosts;
import com.softserveinc.ita.javaclub.volleyblog.model.Player;
import com.softserveinc.ita.javaclub.volleyblog.model.Post;
import com.softserveinc.ita.javaclub.volleyblog.model.PostLike;
import com.softserveinc.ita.javaclub.volleyblog.repository.PlayerRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.PostLikeRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.PostRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportsServiceImpl implements ReportsService{
    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final PostLikeRepository postLikeRepository;

    private final PlayerRepository playerRepository;

    public ReportsServiceImpl(UserRepository userRepository,
                              PostRepository postRepository,
                              PostLikeRepository postLikeRepository,
                              PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public List<TopPlayersWithPosts> getTop10PlayersWithPosts() {
        List<TopPlayersWithPosts> topPlayersWithPostsList = new ArrayList<>();
        List<Player> players = playerRepository.findAll();
        for (Player player : players) {
            List<Post> playerPosts = postRepository.findAllByUser(player.getUser());
            if (!playerPosts.isEmpty()) {
                TopPlayersWithPosts topPlayersWithPosts = new TopPlayersWithPosts();
                topPlayersWithPosts.setPlayerId(player.getPlayerId());
                topPlayersWithPosts.setUserId(player.getUser().getUserId());
                topPlayersWithPosts.setNickName(player.getNickname());
                Integer allPostsRating = 0;
                Short likeValue = 1;
                for (Post post: playerPosts) {
                    Integer postRating = postLikeRepository
                            .countAllByLikeValueAndPost(likeValue, post);
                    allPostsRating += postRating;
                }
                topPlayersWithPosts.setAllPostsRating(allPostsRating);
                topPlayersWithPostsList.add(topPlayersWithPosts);
            }
        }
        return topPlayersWithPostsList.stream()
                .sorted((p1, p2) -> p2.getAllPostsRating().compareTo(p1.getAllPostsRating()))
                .collect(Collectors.toList());
    }



}
