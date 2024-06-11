package com.wanted.market.api.controller;

import com.wanted.market.api.controller.request.JoinUserRequest;
import com.wanted.market.api.controller.request.LoginRequest;
import com.wanted.market.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/join")
    public ApiResponse<String> joinUser(@Valid @RequestBody JoinUserRequest request) {
        try {
            userService.createUser(request.getLoginId(), request.getLoginPw());
        } catch (Exception e) {
            return ApiResponse.of(HttpStatus.CONFLICT, e.getMessage());
        }
        return ApiResponse.ok("가입완료");
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@Valid @RequestBody LoginRequest request) {
        String token = userService.login(request.getLoginId(), request.getLoginPw());
        return ApiResponse.ok(token);
    }
}
