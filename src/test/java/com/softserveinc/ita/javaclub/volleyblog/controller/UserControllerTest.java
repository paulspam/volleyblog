package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.ita.javaclub.volleyblog.model.Permission;
import com.softserveinc.ita.javaclub.volleyblog.model.Role;
import com.softserveinc.ita.javaclub.volleyblog.model.Status;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.service.UserServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserServiceImpl mockUserServiceImpl;


    User RECORD_1 = new User(1, "paul", "Paul", "Musienko", "paul77@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
    User RECORD_2 = new User(2, "paul2", "Paul2", "Musienko2", "paul772@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
    User RECORD_3 = new User(3, "admin", "Admin", "Adminko", "admin@ukr.net", "admin", Status.ACTIVE , new Role(1, "ROLE_ADMIN", List.of(new Permission(), new Permission())));

    @Test
    void findAll_success() throws Exception {
        List<User> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        Integer pageNo = 0;
        Integer pageSize = 3;
        String sortBy = "userId";

        Mockito.when(mockUserServiceImpl.findAll(pageNo, pageSize, sortBy)).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[2].userName", Matchers.is("admin")));
    }
/*
    @Test
    void findById() {
    }

    @Test
    void saveNewUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findAllByFirstName() {
    }

    @Test
    void findAllByLastName() {
    }
    */
}