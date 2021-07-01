package com.example.demotdd.endpoints.update;

import com.example.demotdd.domain.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{id}")
@Api(tags = "Update an existing user with the PUT method")
public class UpdateUserController {
    @Autowired
    private UpdateUserService service;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Execute PUT method")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
        return ResponseEntity.ok().body(service.updateUser(id, user));
    }
}
