package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.dto.TopPlayersWithPosts;

import java.util.List;

public interface ReportsService {
    List<TopPlayersWithPosts> getTop10PlayersWithPosts();
}
