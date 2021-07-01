package com.example.demotdd.endpoints.create;

import com.example.demotdd.domain.user.User;
import com.example.demotdd.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {
    @Autowired
    private UserRepository userRepository;

    public User createNewUser(User user){
        return userRepository.save(user);
    }
}
