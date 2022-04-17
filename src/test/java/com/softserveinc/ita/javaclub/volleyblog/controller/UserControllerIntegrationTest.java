package com.softserveinc.ita.javaclub.volleyblog.controller;

import com.softserveinc.ita.javaclub.volleyblog.model.Permission;
import com.softserveinc.ita.javaclub.volleyblog.model.Role;
import com.softserveinc.ita.javaclub.volleyblog.model.Status;
import com.softserveinc.ita.javaclub.volleyblog.model.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//import static net.bytebuddy.matcher.ElementMatchers.is;
//import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

//@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
@AutoConfigureMockMvc
//@AutoConfigureMockMvc(secure = false)



//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)

@SpringBootTest(webEnvironment = DEFINED_PORT)
public class UserControllerIntegrationTest {

//    @Autowired
//    @MockBean
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext wac;


//    @BeforeAll
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(wac)
//                .apply(springSecurity())
//                .build();
//    }

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
//    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @WithMockUser(username = "admin", password = "admin")
//    @WithUserDetails("admin")
    public void findByIdIT() throws Exception {


//        given().get("/swagger-ui")
        given()
//                .auth().none()
                .get("/users")
                .then()
//                .contentType(ContentType.JSON)
                .statusCode(200);
/*

        String paramUserId = "1";
        User expectedUser = new User(1, "paul", "Paul", "Musienko", "paul77@ukr.net", "psw", Status.ACTIVE, new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        mockMvc.perform(get("/users/2")
//                .with(user())
//                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName", is("paul")));

*/

    }

    @Test
    @Disabled
    public void saveUserIntegrationTest() {
        User user = new User(1, "paul", "Paul", "Musienko", "paul77@ukr.net", "psw", Status.ACTIVE , new Role(2, "ROLE_USER", List.of(new Permission(), new Permission())));
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        String userName = response.jsonPath().getString("userName");
        assertNotNull(userName);

    }

    @Test
    @WithAnonymousUser
    void testUnauthorized() throws Exception {
        given().get("/users")
                .then()
//                .contentType(ContentType.JSON)
                .statusCode(403);
    }

}
