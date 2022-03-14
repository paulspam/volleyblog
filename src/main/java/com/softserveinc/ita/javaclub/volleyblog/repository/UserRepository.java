package com.softserveinc.ita.javaclub.volleyblog.repository;

import com.softserveinc.ita.javaclub.volleyblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer > {
    User findByUserName(String Name);
    List<User> findAllByFirstName (String name);
    List<User> findAllByLastName(String nickName);
}
