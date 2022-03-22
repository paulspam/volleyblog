package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.model.Status;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.repository.RoleRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Override
    public User findById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn("IN findById - user not found by userId {}", id);

        } else {
            log.info("IN findById - user {} found by userId {}", user.getUserName(), id);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> allUser = userRepository.findAll();
        log.info("IN getAll - {} users found", allUser.size());
        return allUser;
    }

    @Override
    public User save(User user) {
        if (userRepository.findByUserName(user.getUserName()) != null) {
            log.info("IN UserServiceImpl.save - user with userName {} already exist", user.getUserName());
            return null;
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            log.info("IN UserServiceImpl.save - user with email {} already exist", user.getEmail());
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        User savedUser = null;
        try {
            savedUser = userRepository.save(user);
            log.info("IN UserServiceImpl.save - user: {} successfully saved", savedUser.getUserName());
        } catch (DataIntegrityViolationException e) {
            log.info("IN UserServiceImpl.save - for user: {} catch DataIntegrityViolationException", savedUser.getUserName());
            return null;
        } catch (Exception e) {
            log.info("IN UserServiceImpl.save - for user: {} catch Exception", savedUser.getUserName());
            return null;
        }
        return savedUser;
    }

    @Override
    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User updatedUser = null;
        try {
            updatedUser = userRepository.save(user);
            log.info("IN UserServiceImpl.update - user: {} successfully updated", updatedUser.getUserName());
        /*
        } catch (AccessDeniedException e) {
            log.info("IN UserServiceImpl.save - for user: {} catch AccessDeniedException", updatedUser.getUserName());
            return null;
        */
        } catch (DataIntegrityViolationException e) {
            log.info("IN UserServiceImpl.save - for user: {} catch DataIntegrityViolationException", user.getUserName());
            return null;
        } catch (Exception e) {
            log.info("IN UserServiceImpl.save - for user: {} catch Exception", user.getUserName());
            return null;
        }

        return updatedUser;
    }

    @Override
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

    @Override
    public List<User> findAllByFirstName(String name) {
        return userRepository.findAllByFirstName(name);
    }

    @Override
    public List<User> findAllByLastName(String lastName) {
        List<User> allByLastName = userRepository.findAllByLastName(lastName);
        return allByLastName;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
