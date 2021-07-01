package com.example.demotdd.endpoints.detail;

import com.example.demotdd.domain.user.User;
import com.example.demotdd.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailUserService {
    @Autowired
    private UserRepository userRepository;

    public User listUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
