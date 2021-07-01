package com.example.demotdd.endpoints.unit.service;

import com.example.demotdd.domain.user.User;
import com.example.demotdd.domain.user.UserRepository;
import com.example.demotdd.endpoints.detail.UserNotFoundException;
import com.example.demotdd.endpoints.update.UpdateUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UpdateUserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUserService service;

    @Test
    public void whenGivenId_shouldUpdateUser_ifFound(){
        User user = new User();
        user.setId(89L);
        user.setName("Toto");

        User newUser = new User();
        newUser.setName("Tata");

        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        service.updateUser(user.getId(), newUser);

        verify(userRepository).save(newUser);
        verify(userRepository).findById(user.getId());
    }

    @Test(expected = UserNotFoundException.class)
    public void should_throw_exception_when_user_doesnt_exist(){
        User user = new User();
        user.setId(89L);
        user.setName("Toto");

        User newUser = new User();
        newUser.setName("Tata");
        newUser.setId(90L);

        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        service.updateUser(user.getId(), newUser);
    }
}
