package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User saveUser(User user);

    void deleteById(Integer id);

    User findByUserName(String name);

    List<User> findAllByFirstName(String name);

    List<User> findAllByLastName(String lastName);
}
