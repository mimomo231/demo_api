package com.example.demo.controller;

import com.example.demo.request.LoginRequest;
import com.example.demo.request.RegisterRequest;
import com.example.demo.response.RegisterResponse;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api/user", produces = "application/json")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/test")
    ResponseEntity<?> testApi() {
        return ResponseEntity.ok("hello");
    }
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request);
    }
//    @PostMapping("/register")
//    ResponseEntity<RegisterResponse> addUser(
//            @RequestBody @Valid RegisterRequest request
//    ) {
//        return ResponseEntity.ok(userService.addUser(request));
//    }
}
