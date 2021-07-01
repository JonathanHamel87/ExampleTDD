package com.example.demotdd.endpoints.unit.controller;

import com.example.demotdd.domain.user.User;
import com.example.demotdd.endpoints.detail.DetailUserController;
import com.example.demotdd.endpoints.detail.DetailUserService;
import com.example.demotdd.endpoints.detail.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DetailUserController.class)
public class DetailUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DetailUserService service;

    @Test
    public void listUserById_whenGetMethod() throws Exception{
        User user = new User();
        user.setName("Toto");
        user.setId(89L);

        given(service.listUser(user.getId())).willReturn(user);

        mockMvc.perform(get("/users/" + user.getId().toString())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name", is(user.getName())));
    }

    @Test
    public void should_throw_exception_when_user_doesnt_exist() throws Exception{
        User user = new User();
        user.setName("Toto");
        user.setId(89L);

        Mockito.doThrow(new UserNotFoundException(user.getId())).when(service).listUser(user.getId());

        mockMvc
                .perform(get("/users/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
