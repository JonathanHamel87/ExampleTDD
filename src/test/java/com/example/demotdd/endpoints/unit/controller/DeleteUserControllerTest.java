package com.example.demotdd.endpoints.unit.controller;

import com.example.demotdd.domain.user.User;
import com.example.demotdd.endpoints.delete.DeleteUserController;
import com.example.demotdd.endpoints.delete.DeleteUserService;
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

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DeleteUserController.class)
public class DeleteUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeleteUserService service;

    @Test
    public void removeUserById_whendDeleteMethod() throws Exception{
        User user = new User();
        user.setName("Toto");
        user.setId(89L);

        doNothing().when(service).deleteUser(user.getId());

        mockMvc
                .perform(delete("/users/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_throw_exception_when_user_doesnt_exist() throws Exception{
        User user = new User();
        user.setName("Toto");
        user.setId(89L);

        Mockito.doThrow(new UserNotFoundException(user.getId())).when(service).deleteUser(user.getId());

        mockMvc
                .perform(delete("/users/" + user.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
