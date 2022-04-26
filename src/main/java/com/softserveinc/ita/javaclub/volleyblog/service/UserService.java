package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.exception.RecordNotFoundException;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.softserveinc.ita.javaclub.volleyblog.security.constants.Permissions.MANAGE_USERS;
import static com.softserveinc.ita.javaclub.volleyblog.security.constants.Permissions.READ_USER;

public interface UserService {

    @PreAuthorize(READ_USER)
    User findById(Integer id) throws RecordNotFoundException;

    @PreAuthorize(MANAGE_USERS)
    List<User> findAll(Integer pageNo, Integer pageSize, String sortBy);

    @PreAuthorize(MANAGE_USERS)
    User save(User user);

    @PreAuthorize("#user.userName == authentication.name")
//    @PreAuthorize("hasAuthority('#user.userName == authentication.name')")
    User update(User user);

    @PreAuthorize(MANAGE_USERS)
    void deleteById(Integer id) throws RecordNotFoundException;

    User findByUserName(String name) throws RecordNotFoundException;

    List<User> findAllByFirstName(String name);

    List<User> findAllByLastName(String lastName);

    User findByEmail(String email) throws RecordNotFoundException;
}
