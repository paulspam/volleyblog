package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.exception.RecordNotFoundException;
import com.softserveinc.ita.javaclub.volleyblog.model.Status;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.repository.RoleRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(Integer id) throws RecordNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User not found by userId " + id));
//        return userRepository.findById(id).orElseThrow(RecordNotFoundException::new);
//
//        Optional<User> user = userRepository.findById(id).orElseThrow(RecordNotFoundException::new);
//
//        if (user.isPresent()) {
//            log.info("IN UserServiceImpl.findById - user {} found by userId {}", user.get().getUserName(), id);
//            return user.get();
//        } else {
//            log.warn("IN UserServiceImpl.findById - user not found by userId {}", id);
//            throw new RecordNotFoundException("User not found by userId " + id);
//        }
    }

    @Override
    public List<User> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<User> pagedResult = userRepository.findAll(paging);

        log.info("IN UserServiceImpl.findAll - {} users found at {} pages",
                pagedResult.getTotalElements(), pagedResult.getTotalPages());

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);

        User savedUser = userRepository.save(user);
        log.info("IN UserServiceImpl.update - user: {} successfully updated", savedUser.getUserName());

        return savedUser;
    }

    @Override
    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User updatedUser = userRepository.save(user);
        log.info("IN UserServiceImpl.update - user: {} successfully updated", updatedUser.getUserName());

        return updatedUser;
    }

    @Override
    public void deleteById(Integer id) throws RecordNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.deleteById(id);
            log.info("IN deleteById - user with id: {} deleted", id);
        } else {
            log.warn("IN UserServiceImpl.deleteById - user not found with userId {}", id);
            throw new RecordNotFoundException("User not found with userId " + id);
        }
    }

    @Override
    public User findByUserName(String name) throws RecordNotFoundException {

        return userRepository.findByUserName(name).orElseThrow(() -> new RecordNotFoundException("User not found by username " + name));
//        return userRepository.findByUserName(name).orElseThrow(RecordNotFoundException::new);

        //        Optional<User> user = userRepository.findByUserName(name);
//        if (user.isPresent()) {
//            log.info("IN findByUserName - user  with username {} found", name);
//            return user.get();
//        } else {
//            log.warn("IN findByUserName - user not found by username {}", name);
//            throw new RecordNotFoundException("User not found by username " + name);
//        }
    }

    @Override
    public List<User> findAllByFirstName(String name) {
        return userRepository.findAllByFirstName(name);
    }

    @Override
    public List<User> findAllByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName);
    }

    @Override
    public User findByEmail(String email) throws RecordNotFoundException {

        return userRepository.findByEmail(email).orElseThrow(() -> new RecordNotFoundException("User not found by email " + email));
//        Optional<User> user = userRepository.findByEmail(email);
//        if (user.isPresent()) {
//            log.info("IN findByUserName - user  with username {} found", email);
//            return user.get();
//        } else {
//            log.warn("IN findByUserName - user not found by username {}", email);
//            throw new RecordNotFoundException("User not found by username " + email);
//        }
    }
}
