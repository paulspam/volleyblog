package com.softserveinc.ita.javaclub.volleyblog.service;


import com.softserveinc.ita.javaclub.volleyblog.repository.PostLikeRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.PostRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReportsServiceImpl implements ReportsService{
    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final PostLikeRepository postLikeRepository;

    public ReportsServiceImpl(UserRepository userRepository,
                              PostRepository postRepository,
                              PostLikeRepository postLikeRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
    }

}
