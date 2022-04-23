package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.ita.javaclub.volleyblog.model.Status;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.repository.RoleRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerIntegrationTest_v2 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @WithMockUser(authorities = "READ_USER")
    public void findByIdIT() throws Exception {
        User testUser = new User(null,
                "paul77Update",
                "Paul77Update",
                "Musienko77Update",
                "paul77update@ukr.net",
                "psw",
                Status.ACTIVE,
                roleRepository.findByRoleName("ROLE_USER"));
        testUser = userRepository.save(testUser);
        Integer userIdToTest = testUser.getUserId();

        mockMvc.perform(get("/users/" + userIdToTest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName", is(testUser.getUserName())))
                .andExpect(jsonPath("$.email", is(testUser.getEmail())));
    }

    @Test
//    @Disabled
    @WithMockUser(authorities = "READ_USER")
    public void findByIdThrowsExceptionIT() throws Exception {
        mockMvc.perform(get("/users/222").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser(authorities = "MANAGE_USERS")
    public void findAllIT() throws Exception {
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(3)));
//                .andExpect(jsonPath("$[1].userName", is("paul")))
//                .andExpect(jsonPath("$[1].email", is("paul77@ukr.net")))
//                .andExpect(jsonPath("$[2].userName", is("paul33")))
//                .andExpect(jsonPath("$[2].email", is("paul773@ukr.net")));

    }

    @Test
    @WithMockUser(authorities = "MANAGE_USERS")
    public void saveNewUserIT() throws Exception {
        User testUser = new User(null,
                "paul77Save",
                "Paul77Save",
                "Musienko77Save",
                "paul77save@ukr.net",
                "psw",
                Status.ACTIVE,
                roleRepository.findByRoleName("ROLE_USER"));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName", is(testUser.getUserName())))
                .andExpect(jsonPath("$.email", is(testUser.getEmail())));
    }

    @Test
    @WithMockUser(authorities = "MANAGE_USERS")
    public void saveNewUserWithExistingUserIdIT() throws Exception {
        User testUser = new User(77,
                "paul77Save",
                "Paul77Save",
                "Musienko77Save",
                "paul77save@ukr.net",
                "psw",
                Status.ACTIVE,
                roleRepository.findByRoleName("ROLE_USER"));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
    }

    @Test
    @WithMockUser(username = "paul77Updated")
    public void updateUserIT() throws Exception {
        User testUser = new User(null,
                "paul77Update",
                "Paul77Update",
                "Musienko77Update",
                "paul77update@ukr.net",
                "psw",
                Status.ACTIVE,
                roleRepository.findByRoleName("ROLE_USER"));
        testUser = userRepository.save(testUser);
        testUser.setUserName("paul77Updated");
        testUser.setFirstName("Paul77Updated");
        testUser.setEmail("paul77updated@ukr.net");

        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName", is(testUser.getUserName())))
                .andExpect(jsonPath("$.firstName", is(testUser.getFirstName())))
                .andExpect(jsonPath("$.email", is(testUser.getEmail())));
    }

    @Test
    @WithMockUser(username = "paul77Update")
    public void updateUserWithoutUserIdIT() throws Exception {
        User testUser = new User(null,
                "paul77Update",
                "Paul77Update",
                "Musienko77Update",
                "paul77update@ukr.net",
                "psw",
                Status.ACTIVE,
                roleRepository.findByRoleName("ROLE_USER"));

        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    @WithMockUser(authorities = {"MANAGE_USERS", "READ_USER"})
    public void deleteByIdIT() throws Exception {
        User testUser = new User(null,
                "paul77Delete",
                "Paul77Delete",
                "Musienko77Delete",
                "paul77delete@ukr.net",
                "psw",
                Status.ACTIVE,
                roleRepository.findByRoleName("ROLE_USER"));

        testUser = userRepository.save(testUser);
        Integer userIdToTest = testUser.getUserId();

        mockMvc.perform(delete("/users/" + userIdToTest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        assertNull(userRepository.findById(userIdToTest).orElse(null));
    }

    @Test
    @WithMockUser(authorities = {"MANAGE_USERS", "READ_USER"})
    public void deleteByIdThrowsExceptionIT() throws Exception {
        mockMvc.perform(delete("/users/555")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void deleteByIdReturnsAccessDeniedIT() throws Exception {
        mockMvc.perform(delete("/users/444")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    public void findAllByFirstNameIT() throws Exception {
        User testUser = new User(null,
                "paul77Test",
                "Paul77",
                "Musienko77Test",
                "paul77test@ukr.net",
                "psw",
                Status.ACTIVE,
                roleRepository.findByRoleName("ROLE_USER"));
        testUser = userRepository.save(testUser);
        String firstName = testUser.getFirstName();

        mockMvc.perform(get("/users/firstname/" + firstName).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName", is(firstName)));
    }

    @Test
    @WithMockUser
    public void findAllByLastNameIT() throws Exception {
        User testUser = new User(null,
                "paul77Test",
                "Paul77",
                "Musienko77Test",
                "paul77test@ukr.net",
                "psw",
                Status.ACTIVE,
                roleRepository.findByRoleName("ROLE_USER"));
        testUser = userRepository.save(testUser);
        String lastName = testUser.getLastName();

        mockMvc.perform(get("/users/lastname/" + lastName).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].lastName", is(lastName)));
    }
}
