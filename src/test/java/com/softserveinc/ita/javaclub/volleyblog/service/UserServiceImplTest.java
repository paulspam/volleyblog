package com.softserveinc.ita.javaclub.volleyblog.service;

import com.softserveinc.ita.javaclub.volleyblog.exception.RecordNotFoundException;
import com.softserveinc.ita.javaclub.volleyblog.model.Permission;
import com.softserveinc.ita.javaclub.volleyblog.model.Role;
import com.softserveinc.ita.javaclub.volleyblog.model.Status;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    private PasswordEncoder mockPasswordEncoder;

    @Autowired
    @InjectMocks
    private UserServiceImpl userService;

    private User user1;
    private User user2;
    private User user3;
    List<User> userList;

    @BeforeEach
    public void setUp() {
        userList = new ArrayList<>();
        user1 = new User(1, "paul", "Paul", "Musienko", "paul77@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        user2 = new User(2, "paul2", "Paul2", "Musienko2", "paul772@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        user3 = new User(3, "admin", "Admin", "Adminko", "admin@ukr.net", "admin", Status.ACTIVE , new Role(1, "ROLE_ADMIN", List.of(new Permission(), new Permission())));
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
    }

    @AfterEach
    public void tearDown() {
        user1 = null;
        user2 = null;
        user3 = null;
        userList = null;
    }

    @Test
    void findUserByIdTest() throws RecordNotFoundException {
        when(mockUserRepository.findById(1)).thenReturn(Optional.ofNullable(user1));
        assertThat(userService.findById(user1.getUserId()), equalTo(user1));
    }

    @Test
    void findAllUsersTest() {
        Pageable paging = PageRequest.of(0, 3, Sort.by("userId"));
        Page<User> pages = new PageImpl<>(userList, paging, userList.size());
        when(mockUserRepository.findAll(paging)).thenReturn(pages);
        List<User> returnedUserList = userService.findAll(0, 3, "userId");
        assertEquals(userList, returnedUserList);
        verify(mockUserRepository, times(1)).findAll(paging);
    }

    @Test
    void saveUserTest() {
        when(mockUserRepository.save(any())).thenReturn(user1);

        User savedUser = userService.save(user1);
        assertEquals(user1, savedUser);
    }

    @Test
    void deleteByIdUserTest() throws RecordNotFoundException {
        when(mockUserRepository.findById(any())).thenReturn(Optional.ofNullable(user2));
        doNothing().when(mockUserRepository).deleteById(isA(Integer.class));
        userService.deleteById(2);
        verify(mockUserRepository, times(1)).deleteById(2);
    }

    @Test
    void deleteByIdThrowsExceptionTest() throws RecordNotFoundException {
        when(mockUserRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> userService.deleteById(5));
    }
}