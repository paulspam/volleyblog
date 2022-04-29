package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.softserveinc.ita.javaclub.volleyblog.dto.PlayersWithPostsDto;
import com.softserveinc.ita.javaclub.volleyblog.service.ReportsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportsServiceImpl reportsService;

    public ReportController(ReportsServiceImpl reportsService) {
       this.reportsService = reportsService;
    }

    @GetMapping
    public ResponseEntity<List<PlayersWithPostsDto>> getTopPlayersWithPosts() {
        return ResponseEntity.ok(reportsService.getTop10PlayersWithPosts());
    }


}
