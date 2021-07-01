package com.example.demotdd.endpoints.unit.controller;

import com.example.demotdd.domain.user.User;
import com.example.demotdd.endpoints.detail.UserNotFoundException;
import com.example.demotdd.endpoints.update.UpdateUserController;
import com.example.demotdd.endpoints.update.UpdateUserService;
import com.example.demotdd.endpoints.util.JsonUtil;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UpdateUserController.class)
public class UpdateUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UpdateUserService service;

    @Test
    public void updateUser_whenPutUser() throws Exception{
        User user = new User();
        user.setName("Toto");
        user.setId(89L);

        given(service.updateUser(user.getId(), user)).willReturn(user);

        mockMvc
                .perform(put("/users/"+user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(user.getName())));
    }

    @Test
    public void should_throw_exception_when_user_doesnt_exist() throws Exception{
        User user = new User();
        user.setName("Toto");
        user.setId(89L);

        Mockito.doThrow(new UserNotFoundException(user.getId())).when(service).updateUser(user.getId(), user);

        mockMvc
                .perform(put("/users/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(user)))
                .andExpect(status().isNotFound());
    }
}
