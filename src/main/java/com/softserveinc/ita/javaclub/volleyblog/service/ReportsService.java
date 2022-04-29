package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.dto.PlayersWithPostsDto;

import java.util.List;

public interface ReportsService {
    List<PlayersWithPostsDto> getTop10PlayersWithPosts();
}
