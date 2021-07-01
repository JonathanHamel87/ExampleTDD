package com.example.demotdd.endpoints.unit.controller;

import com.example.demotdd.domain.user.User;
import com.example.demotdd.endpoints.list.ListUserController;
import com.example.demotdd.endpoints.list.ListUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ListUserController.class)
public class ListUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListUserService service;

    @Test
    public void listAllUsers_whenGetMethod() throws Exception{
        User user = new User();
        user.setName("Toto");

        List<User> users = Arrays.asList(user);

        given(service.listAllUsers()).willReturn(users);

        mockMvc
                .perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(user.getName())));
    }
}
