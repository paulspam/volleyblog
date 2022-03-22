package com.softserveinc.ita.javaclub.volleyblog.repository;

import com.softserveinc.ita.javaclub.volleyblog.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
