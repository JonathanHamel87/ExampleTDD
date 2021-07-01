package com.example.demotdd.endpoints.detail;

import com.example.demotdd.domain.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{id}")
@Api(tags = "List an existing user with the GET method")
public class DetailUserController {
    @Autowired
    private DetailUserService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Execute GET method")
    public ResponseEntity<User> list(@PathVariable Long id){
        return ResponseEntity.ok().body(service.listUser(id));
    }
}
