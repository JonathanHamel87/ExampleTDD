package com.example.demotdd.endpoints.unit.service;

import com.example.demotdd.domain.user.User;
import com.example.demotdd.domain.user.UserRepository;
import com.example.demotdd.endpoints.create.CreateUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserService createUserService;

    @Test
    public void whenSaveUser_shouldReturnUser(){
        User user = new User();
        user.setName("Toto");

        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);

        User created = createUserService.createNewUser(user);

        assertThat(created.getName()).isSameAs(user.getName());
        verify(userRepository).save(user);
    }
}
