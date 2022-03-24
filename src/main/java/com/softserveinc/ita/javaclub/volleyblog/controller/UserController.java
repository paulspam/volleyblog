package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAll() {
        List<User> allUsers = userService.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable int id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity("No user with userId = " + id, HttpStatus.NOT_ACCEPTABLE);
        } else{ return ResponseEntity.ok(user);}

    }

    @PostMapping()
    public ResponseEntity<User> saveNewUser(@RequestBody User user) {
        if ((user.getUserId() != null) && (user.getUserId() != 0)) {
            return new ResponseEntity("Redundant parameter: userId must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        User newUser = userService.save(user);
        if (newUser != null) {
            return ResponseEntity.ok(newUser);
        } else {
            return new ResponseEntity("User not saved", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping()
    @PreAuthorize("#user.userName == authentication.name")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if ((user.getUserId() == null) || (user.getUserId() ==0)) {
            return new ResponseEntity("Missing parameter: userId must be not null", HttpStatus.NOT_ACCEPTABLE);
        }
        User updatedUser = userService.update(user);

        /*
        User updatedUser = null;
        try {
            updatedUser = userService.update(user);
        } catch (AccessDeniedException e) {
            return new ResponseEntity("Access denied!", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        return ResponseEntity.ok(updatedUser);

/*

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return new ResponseEntity("User not updated", HttpStatus.UNPROCESSABLE_ENTITY);
        }
*/
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity("No user with userId = " + id, HttpStatus.NOT_ACCEPTABLE);
        } else {
            userService.deleteById(id);
            return ResponseEntity.ok("User with ID = " + id + " was deleted");
        }
    }

    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<List<User>> findAllByFirstName(@PathVariable String firstName) {
        List<User> allByFirstName = userService.findAllByFirstName(firstName);
        return ResponseEntity.ok(allByFirstName);
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<User>> findAllByLastName(@PathVariable String lastName) {
        List<User> allByLastName = userService.findAllByLastName(lastName);
        return ResponseEntity.ok(allByLastName);
    }
}
