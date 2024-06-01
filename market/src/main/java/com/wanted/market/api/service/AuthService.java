package com.wanted.market.api.service;

import com.wanted.market.domain.users.Users;
import com.wanted.market.domain.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Users user = usersRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return User.builder()
                .username(user.getLoginId())
                .password(user.getEncryptedPw())
                .authorities(new ArrayList<>())
                .build();
    }

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
}
