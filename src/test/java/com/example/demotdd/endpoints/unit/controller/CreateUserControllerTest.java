package com.example.demotdd.endpoints.unit.controller;

import com.example.demotdd.domain.user.User;
import com.example.demotdd.endpoints.create.CreateUserController;
import com.example.demotdd.endpoints.create.CreateUserService;
import com.example.demotdd.endpoints.util.JsonUtil;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CreateUserController.class)
public class CreateUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserService service;

    @Test
    public void createUser_whenPostMethod() throws Exception{
        User user = new User();
        user.setName("Toto");

        given(service.createNewUser(user)).willReturn(user);

        mockMvc
                .perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(user.getName())));
    }
}
