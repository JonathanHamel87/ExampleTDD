package com.example.demotdd.endpoints.create;

import com.example.demotdd.domain.user.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@Api(tags = "Create a new user with the POST method")
public class CreateUserController {
    @Autowired
    private CreateUserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Execute POST method")
    public ResponseEntity<User> createNewUser_whenPostUser(@RequestBody User user){
        User createdUser = service.createNewUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdUser);
    }
}
