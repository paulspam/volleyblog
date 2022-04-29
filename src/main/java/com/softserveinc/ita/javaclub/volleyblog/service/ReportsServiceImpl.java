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
public class ReportsServiceImpl implements ReportsService{
    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final PostLikeRepository postLikeRepository;

    private final PlayerRepository playerRepository;

    private final MappingUtils mappingUtils;

    public ReportsServiceImpl(UserRepository userRepository,
                              PostRepository postRepository,
                              PostLikeRepository postLikeRepository,
                              PlayerRepository playerRepository,
                              MappingUtils mappingUtils) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
        this.playerRepository = playerRepository;
        this.mappingUtils = mappingUtils;
    }

    @Override
    public List<PlayersWithPostsDto> getTop10PlayersWithPosts() {
        List<PlayersWithPostsDto> playersWithPostsDtoList = new ArrayList<>();
        List<Player> players = playerRepository.findAll();
        for (Player player : players) {
            List<Post> playerPosts = postRepository.findAllByUser(player.getUser());
            if (!playerPosts.isEmpty()) {
                PlayersWithPostsDto playersWithPostsDto = mappingUtils.mapToPlayersWithPostsDto(player);
                Integer allPostsRating = 0;
                Short likeValue = 1;
                for (Post post: playerPosts) {
                    allPostsRating += postLikeRepository
                            .countAllByLikeValueAndPost(likeValue, post);
                }
                playersWithPostsDto.setAllPostsRating(allPostsRating);
                playersWithPostsDtoList.add(playersWithPostsDto);
            }
        }
        return playersWithPostsDtoList.stream()
                .sorted((p1, p2) -> p2.getAllPostsRating().compareTo(p1.getAllPostsRating()))
                .limit(2)
                .collect(Collectors.toList());
    }



}
