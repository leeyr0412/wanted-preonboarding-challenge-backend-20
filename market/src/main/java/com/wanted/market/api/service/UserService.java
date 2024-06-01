package com.wanted.market.api.service;

import com.wanted.market.domain.users.Users;
import com.wanted.market.domain.users.UsersRepository;
import com.wanted.market.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public String createUser(String loginId, String loginPw) {
        Optional<Users> findUser = usersRepository.findByLoginId(loginId);
        if (findUser.isPresent()) {
            throw new DuplicateKeyException("중복된 아이디입니다.");
        }
        Users newUser = Users.builder()
                .loginId(loginId)
                .encryptedPw(passwordEncoder.encode(loginPw))
                .build();
        usersRepository.save(newUser);
        return "가입완료";
    }

    public String login(String loginId, String loginPw) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginId, loginPw)
            );

            return jwtUtil.generateToken(loginId);

        } catch (AuthenticationException e) {
            throw new RuntimeException("계정 정보를 확인하세요");
        }
    }
}
