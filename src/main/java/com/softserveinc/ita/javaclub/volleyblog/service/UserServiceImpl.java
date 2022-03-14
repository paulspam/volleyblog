package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.Role;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.repository.RoleRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        Role roleUser = roleRepository.findByRoleName("ROLE_USER");
        user.setRole(roleUser);
        return userRepository.save(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findByUserName(name);
    }

    public List<User> findAllByFirstName(String name) {
        return userRepository.findAllByFirstName(name);
    }

    public List<User> findAllByLastName(String lastName) {
        List<User> allByLastName = userRepository.findAllByLastName(lastName);
        return allByLastName;
    }
}
