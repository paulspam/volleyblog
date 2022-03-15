package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.Status;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.repository.RoleRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;


//    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.info("IN findById - user {} found by userId {}", user, id);
        } else {
            log.warn("IN findByUserName - user not found by userId {}", id);
        }
        return user;
    }

    public List<User> findAll() {
        List<User> allUser = userRepository.findAll();
        log.info("IN getAll - {} users found", allUser.size());
        return allUser;
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        User savedUser = userRepository.save(user);
        log.info("IN saveUser - user: {} successfully saved", savedUser);
        return savedUser;
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
        log.info("IN deleteById - user with id: {} deleted", id);
    }

    @Override
    public User findByUserName(String name) {
        User user = userRepository.findByUserName(name);
        if (user != null) {
            log.info("IN findByUserName - user {} found by username {}", user, name);
        } else {
            log.warn("IN findByUserName - user not found by username {}", name);
        }
        return user;
    }

    public List<User> findAllByFirstName(String name) {
        return userRepository.findAllByFirstName(name);
    }

    public List<User> findAllByLastName(String lastName) {
        List<User> allByLastName = userRepository.findAllByLastName(lastName);
        return allByLastName;
    }
}
