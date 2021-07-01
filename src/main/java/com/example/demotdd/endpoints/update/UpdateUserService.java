package com.example.demotdd.endpoints.update;

import com.example.demotdd.domain.user.User;
import com.example.demotdd.domain.user.UserRepository;
import com.example.demotdd.endpoints.detail.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService {
    @Autowired
    private UserRepository userRepository;

    public User updateUser(Long id, User user) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setId(id);
        return userRepository.save(user);
    }
}
