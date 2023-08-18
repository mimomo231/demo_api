package com.example.demo.controller;

import com.example.demo.common.base.controller.BaseController;
import com.example.demo.common.security.model.UserPrincipal;
import com.example.demo.model.ThanhVien;
import com.example.demo.request.*;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api/user", produces = "application/json")
@RequiredArgsConstructor
public class UserController extends BaseController {
    private final UserService userService;
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/reset-password")
    ResponseEntity<String> resetPassword(
            @RequestBody @Valid ResetPasswordRequest request
    ) {
        userService.resetPassword(request);
        return ResponseEntity.ok("Reset password completed");
    }
    @PostMapping("/refresh-token")
    ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
//        return userService.refreshToken(request);
        return userService.refreshToken1(request);
    }

    @GetMapping("/ds/{lid}")
    ResponseEntity<?> getListUserInClass(@PathVariable Integer lid){
        return userService.getListUserInClass(lid);
    }

    @GetMapping
    ResponseEntity<?> searchUser(@RequestParam(name = "key") String key,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "3") int size,
                                 @RequestParam(defaultValue = "") String sortBy) {
        return userService.searchUser(key, page, size, sortBy);
    }

    @PostMapping
    ResponseEntity<?> addUser(
            @RequestBody @Valid AddUserRequest request
    ) {
        return ResponseEntity.ok(userService.addUser(request));
    }

    @PutMapping
    ResponseEntity<?> updateUser(@RequestBody @Valid UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(request));
    }

    @DeleteMapping("/{uid}")
    ResponseEntity<?> deleteUser(@PathVariable Integer uid) {
        return ResponseEntity.ok(userService.deleteUser(uid));
    }
}
