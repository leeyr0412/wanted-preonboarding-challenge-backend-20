package com.wanted.market.api.controller;

import com.wanted.market.api.controller.request.JoinUserRequest;
import com.wanted.market.api.controller.request.LoginRequest;
import com.wanted.market.api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/join")
    public ApiResponse<String> joinUser(@Valid @RequestBody JoinUserRequest request) {
        try {
            authService.createUser(request.getLoginId(), request.getLoginPw());
        } catch (Exception e) {
            return ApiResponse.of(e.getMessage());
        }
        return ApiResponse.ok("가입완료");
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@Valid @RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getLoginPw())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ApiResponse.ok("로그인 성공");
        } catch (AuthenticationException e) {
            return ApiResponse.of("계정 정보를 확인하세요");
        }
    }
}
