package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.ita.javaclub.volleyblog.exception.RecordNotFoundException;
import com.softserveinc.ita.javaclub.volleyblog.model.Permission;
import com.softserveinc.ita.javaclub.volleyblog.model.Role;
import com.softserveinc.ita.javaclub.volleyblog.model.Status;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    @WithUserDetails("admin")
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
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].userName", is("admin")));
    }

    @Test
    void findById_success() throws Exception {
        Mockito.when(mockUserServiceImpl.findById(RECORD_1.getUserId())).thenReturn(RECORD_1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.username", is("paul")));
    }

    @Test
    void saveNewUser() throws Exception {
        User newUser = User.builder()
                .userName("paul77")
                .firstName("Paul77")
                .lastName("Musienko77")
                .email("paul777@ukr.net")
                .password("psw")
                .status(Status.ACTIVE)
                .role(new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())))
                .build();

        Mockito.when(mockUserServiceImpl.save(newUser)).thenReturn(newUser);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.userName", is("paul77")));
    }

    @Test
    void updateUser_success() throws Exception {
        User updatedUser = User.builder()
                .userId(1)
                .userName("paul")
                .firstName("Paul222")
                .lastName("Musienko")
                .email("paul77@ukr.net")
                .password("psw")
                .status(Status.ACTIVE)
                .role(new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())))
                .build();

        Mockito.when(mockUserServiceImpl.findById(RECORD_1.getUserId())).thenReturn(RECORD_1);
        Mockito.when(mockUserServiceImpl.save(updatedUser)).thenReturn(updatedUser);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.firstName", is("Paul222")));
    }

    @Test
    public void deleteById_success() throws Exception {
        Mockito.when(mockUserServiceImpl.findById(RECORD_2.getUserId())).thenReturn(RECORD_2);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteById_notFound() throws Exception {
        Mockito.when(mockUserServiceImpl.findById(5)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/users/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof RecordNotFoundException))
                .andExpect(result ->
                        assertEquals("Patient with ID 5 does not exist.", result.getResolvedException().getMessage()));
    }














    /*
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