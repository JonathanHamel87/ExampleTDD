package com.example.demotdd.endpoints.unit.service;

import com.example.demotdd.domain.user.User;
import com.example.demotdd.domain.user.UserRepository;
import com.example.demotdd.endpoints.detail.DetailUserService;
import com.example.demotdd.endpoints.detail.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailUserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DetailUserService service;

    @Test
    public void whenGivenId_shouldReturnUser_ifFound(){
        User user = new User();
        user.setId(89L);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User expected = service.listUser(user.getId());

        assertThat(expected).isSameAs(user);
        verify(userRepository).findById(user.getId());
    }

    @Test(expected = UserNotFoundException.class)
    public void should_throw_exception_when_user_doesnt_exist(){
        User user = new User();
        user.setId(89L);
        user.setName("Toto");

        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        service.listUser(user.getId());
    }
}
