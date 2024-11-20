package com.futurenostics.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.futurenostics.model.User;
import com.futurenostics.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestParam("userRequest") String userRequestJson) throws JsonProcessingException {

        User userRequest = new ObjectMapper().readValue(userRequestJson, User.class);

        return userService.createUser(userRequest);
    }
}
