package com.softserveinc.ita.javaclub.volleyblog.repository;

import com.softserveinc.ita.javaclub.volleyblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer > {
    Optional<User> findByUserName(String Name);
    List<User> findAllByFirstName (String name);
    List<User> findAllByLastName(String nickName);
    Optional<User> findByEmail(String email);
}
