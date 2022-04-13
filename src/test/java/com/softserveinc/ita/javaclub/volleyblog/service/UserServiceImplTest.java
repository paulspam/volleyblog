package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.exception.RecordNotFoundException;
import com.softserveinc.ita.javaclub.volleyblog.model.Permission;
import com.softserveinc.ita.javaclub.volleyblog.model.Role;
import com.softserveinc.ita.javaclub.volleyblog.model.Status;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private BCryptPasswordEncoder mockPasswordEncoder;

    @Autowired
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findUserByIdTest() throws RecordNotFoundException {
        Integer userId = 1;
        User user = new User(1, "paul", "Paul", "Musienko", "paul77@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        when(mockUserRepository.findById(userId)).thenReturn(Optional.ofNullable(user));
        assertThat(userService.findById(user.getUserId()), equalTo(user));
    }

    @Test
    void findUserByIdThrowsExceptionTest() {
        when(mockUserRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> userService.findById(555));
    }

    @Test
    void findAllUsersTest() {
        List<User> userList = new ArrayList<>();
        User user1 = new User(1, "paul", "Paul", "Musienko", "paul77@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        User user2 = new User(2, "paul2", "Paul2", "Musienko2", "paul772@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        User user3 = new User(3, "admin", "Admin", "Adminko", "admin@ukr.net", "admin", Status.ACTIVE , new Role(1, "ROLE_ADMIN", List.of(new Permission(), new Permission())));
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        Pageable paging = PageRequest.of(0, 3, Sort.by("userId"));
        Page<User> pages = new PageImpl<>(userList, paging, userList.size());
        when(mockUserRepository.findAll(paging)).thenReturn(pages);
        List<User> returnedUserList = userService.findAll(0, 3, "userId");
        assertEquals(userList, returnedUserList);
        verify(mockUserRepository, times(1)).findAll(paging);
    }

    @Test
    void findAllUsersReturnsEmptyListTest() {
        Page<User> emptyPages = new PageImpl<User>(new ArrayList<>(), PageRequest.of(0, 3, Sort.by("userId")), 0);
        when(mockUserRepository.findAll(any(Pageable.class))).thenReturn(emptyPages);
        List<User> returnedUserList = userService.findAll(0, 3, "userId");
        assertEquals(new ArrayList<>(), returnedUserList);
        verify(mockUserRepository, times(1)).findAll(any(Pageable.class));

    }

    @Test
    void saveUserTest() {
        User user = new User(1, "paul", "Paul", "Musienko", "paul77@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        when(mockUserRepository.save(any())).thenReturn(user);
        User savedUser = userService.save(user);
        assertEquals(user, savedUser);
    }

    @Test
    void updateUserTest() {
        User user = new User(1, "paul", "Paul", "Musienko", "paul77@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        when(mockUserRepository.save(any())).thenReturn(user);
        User savedUser = userService.update(user);
        assertEquals(user, savedUser);
    }


    @Test
    void deleteByIdUserTest() throws RecordNotFoundException {
        Integer userId = 1;
        User user = new User(1, "paul", "Paul", "Musienko", "paul77@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        when(mockUserRepository.findById(any())).thenReturn(Optional.ofNullable(user));
        doNothing().when(mockUserRepository).deleteById(isA(Integer.class));
        userService.deleteById(userId);
        verify(mockUserRepository, times(1)).deleteById(userId);
    }

    @Test
    void deleteByIdThrowsExceptionTest() throws RecordNotFoundException {
        when(mockUserRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> userService.deleteById(5));
    }

    @Test
    void findByUserNameTest() throws RecordNotFoundException {
        String userName = "paul";
        User user = new User(1, "paul", "Paul", "Musienko", "paul77@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        when(mockUserRepository.findByUserName(userName)).thenReturn(Optional.ofNullable(user));
        assertThat(userService.findByUserName(user.getUserName()), equalTo(user));

    }

    @Test
    void findAllByFirstNameTest() {
        String firstName = "Paul";
        List<User> userListToReturn = new ArrayList<>();
        List<User> expectedUserList = new ArrayList<>();
        User user1 = new User(1, "paul", "Paul", "Musienko", "paul77@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        User user2 = new User(2, "paul2", "Paul", "Musienko2", "paul772@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        userListToReturn.add(user1);
        userListToReturn.add(user2);
        expectedUserList.addAll(userListToReturn);
        when(mockUserRepository.findAllByFirstName(firstName)).thenReturn(userListToReturn);
        List<User> returnedUserList = userService.findAllByFirstName(firstName);
        assertEquals(expectedUserList, returnedUserList);
        verify(mockUserRepository, times(1)).findAllByFirstName(firstName);
    }

    @Test
    void findAllByLastNameTest() {
        String lastName = "Musienko";
        List<User> userListToReturn = new ArrayList<>();
        List<User> expectedUserList = new ArrayList<>();
        User user1 = new User(1, "paul", "Paul", "Musienko", "paul77@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        User user2 = new User(2, "paul2", "Paul2", "Musienko", "paul772@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        userListToReturn.add(user1);
        userListToReturn.add(user2);
        expectedUserList.addAll(userListToReturn);
        when(mockUserRepository.findAllByLastName(lastName)).thenReturn(userListToReturn);
        List<User> returnedUserList = userService.findAllByLastName(lastName);
        assertEquals(expectedUserList, returnedUserList);
        verify(mockUserRepository, times(1)).findAllByLastName(lastName);
    }

    @Test
    void findByEmailTest() throws RecordNotFoundException {
        String email = "paul77@ukr.net";
        User user = new User(1, "paul", "Paul", "Musienko", "paul77@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        when(mockUserRepository.findByEmail(email)).thenReturn(Optional.ofNullable(user));
        assertThat(userService.findByEmail(user.getEmail()), equalTo(user));
    }

}