package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.softserveinc.ita.javaclub.volleyblog.model.Permission;
import com.softserveinc.ita.javaclub.volleyblog.model.Role;
import com.softserveinc.ita.javaclub.volleyblog.model.Status;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import com.softserveinc.ita.javaclub.volleyblog.repository.RoleRepository;
import com.softserveinc.ita.javaclub.volleyblog.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest_v2 {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private ObjectMapper objectMapper;

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    @WithMockUser(username = "admin", password = "admin11")
    public void findByIdIT() throws Exception {
        Integer userIdToTest = 1;

        User userToTest = userRepository.findById(userIdToTest).orElseThrow();
        mockMvc.perform(get("/users/" + userIdToTest)
//                .with(user())
//                .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName", is(userToTest.getUserName())))
                .andExpect(jsonPath("$.email", is(userToTest.getEmail())));
//                .andExpect(jsonPath("$[0].userName", is("paul")));

    }

    @Test
    @Disabled
    @WithMockUser(username = "admin", password = "admin")
    public void findByIdThrowsExceptionIT() throws Exception {
        mockMvc.perform(get("/users/222").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


    @Test
    @WithMockUser(username = "admin", password = "admin")
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
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void saveNewUserIT() throws Exception {
        User newUser = new User(null, "paul777", "Paul777", "Musienko777", "paul7777@ukr.net", "psw", Status.ACTIVE, new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        mockMvc.perform(post("/users")
//                        .with(user("admin"))
//                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
//                        .content(asJsonString(newUser)))
                        .content(mapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName", is("paul777")));

    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void updateUserIT() throws Exception {
        User newUser = new User(5, "paul5", "Paul55", "Musienko5", "paul775@ukr.net", "psw", Status.ACTIVE, new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        mockMvc.perform(put("/users")
//                        .with(user("admin"))
//                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(mapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName", is("paul5")))
                .andExpect(jsonPath("$.firstName", is("Paul55")))
                .andExpect(jsonPath("$.email", is("paul775@ukr.net")));
    }

    @Test
//    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @WithMockUser(authorities = "MANAGE_USERS")
    public void deleteByIdIT() throws Exception {

        User testUser = new User(null,
                "paul777",
                "Paul777",
                "Musienko777",
                "paul777@ukr.net",
                "psw",
                Status.ACTIVE,
                roleRepository.findByRoleName("ROLE_USER"));

        testUser = userRepository.save(testUser);
        Integer userIdToTest = testUser.getUserId();

        mockMvc.perform(delete("/users/" + userIdToTest)
//                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
//                .with(csrf().asHeader()))
                .andDo(print())
                .andExpect(status().isOk());
        assertNull(userRepository.findById(userIdToTest).orElse(null));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void deleteByIdThrowsExceptionIT() throws Exception {
        mockMvc.perform(delete("/users/555"))
                /*.andDo(print())*/
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "paul2", password = "pws", roles = "USER")
    public void deleteByIdReturnsAccessDeniedIT() throws Exception {
        mockMvc.perform(delete("/users/4"))
                /*.andDo(print())*/
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void findAllByFirstNameIT() throws Exception {
        mockMvc.perform(get("/users/firstname/Paul").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName", is("paul")));

    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void findAllByLastNameIT() throws Exception {
        mockMvc.perform(get("/users/lastname/Musiewnko").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].lastName", is("Musienko")));

    }







}
