package com.softserveinc.ita.javaclub.volleyblog.repository;

import com.softserveinc.ita.javaclub.volleyblog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String name);
}
