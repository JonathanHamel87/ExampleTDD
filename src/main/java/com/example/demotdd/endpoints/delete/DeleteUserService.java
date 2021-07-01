package com.example.demotdd.endpoints.delete;

import com.example.demotdd.domain.user.UserRepository;
import com.example.demotdd.endpoints.detail.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService {
    @Autowired
    private UserRepository userRepository;

    public void deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }
}
