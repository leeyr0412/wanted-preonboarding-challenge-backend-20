package com.wanted.market.util.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    private static final List<String> EXCLUDE_URLS = List.of(
            "/auth/join",
            "/auth/login"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestPath = request.getRequestURI();
        if (EXCLUDE_URLS.contains(requestPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        String loginId = null;
        String authToken = null;
        if (header != null && header.startsWith("Bearer ")) {
            authToken = header.replace("Bearer ", "");
            if (jwtUtil.validateToken(authToken)) {
                loginId = jwtUtil.getLoginIdFromToken(authToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);


            } else {
                throw new RuntimeException();
            }
        } else {
            throw new RuntimeException();
        }
        filterChain.doFilter(request, response);
    }
}
