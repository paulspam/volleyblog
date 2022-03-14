package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        } else return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<User> saveNewUser(@RequestBody User user) {
        if ((user.getUserId() != null) && (user.getUserId() !=0)) {
            return new ResponseEntity("Redundant parameter: userId must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if ((user.getUserId() == null) || (user.getUserId() ==0)) {
            return new ResponseEntity("Missing parameter: userId must be not null", HttpStatus.NOT_ACCEPTABLE);
        }
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
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
