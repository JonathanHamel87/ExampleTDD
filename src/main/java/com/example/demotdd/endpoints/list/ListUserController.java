package com.example.demotdd.endpoints.list;

import com.example.demotdd.domain.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "List all existing users whit the GET method")
public class ListUserController {
    @Autowired
    private ListUserService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Execute GET method")
    public ResponseEntity<List<User>> listAllUsers(){
        return ResponseEntity.ok().body(service.listAllUsers());
    }
}
