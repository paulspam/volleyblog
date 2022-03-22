package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.User;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.softserveinc.ita.javaclub.volleyblog.security.constants.Permissions.MANAGE_USERS;

public interface UserService {

    User findById(Integer id);

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PreAuthorize(MANAGE_USERS)
    List<User> findAll();

    @PreAuthorize(MANAGE_USERS)
    User save(User user);

//    @PreAuthorize("#user.userName == authentication.name")
    User update(User user);

    @PreAuthorize(MANAGE_USERS)
    void deleteById(Integer id);

    User findByUserName(String name);

    List<User> findAllByFirstName(String name);

    List<User> findAllByLastName(String lastName);

    User findByEmail(String email);
}
