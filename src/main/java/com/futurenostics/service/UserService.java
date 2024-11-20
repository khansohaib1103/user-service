package com.futurenostics.service;

import com.futurenostics.model.User;
import com.futurenostics.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<String> createUser(User userRequest){
        if (!userRepository.existsByUserName(userRequest.getUserName())) {
            String userName = userRequest.getUserName();
            try {
                User user = User.builder()
                        .userName(userRequest.getUserName())
                        .userEmail(userRequest.getUserEmail())
                        .userPassword(userRequest.getUserPassword())
                        .roles(Set.of("USER"))
                        .build();
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("User " + userName + " saved successfully...");
            } catch (NullPointerException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving user " + userName);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User name already taken.");
    }
}
