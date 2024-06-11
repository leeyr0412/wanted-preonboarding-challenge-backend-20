package com.wanted.market.api.controller;

import com.wanted.market.api.controller.request.RegisterProductRequest;
import com.wanted.market.api.controller.response.ProductResponse;
import com.wanted.market.api.controller.response.RegisterProductResponse;
import com.wanted.market.api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<?> registerProduct(@Valid @RequestBody RegisterProductRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            String loginId = userDetails.getUsername();
            RegisterProductResponse response = productService.registerProduct(request.toDto(loginId));
            return ApiResponse.ok(response);
        }
        return ApiResponse.of(HttpStatus.BAD_REQUEST, "로그인이 필요합니다.");
    }

    @GetMapping
    public ApiResponse<?> getAllProducts() {
        List<ProductResponse> responses = productService.getAllProducts();
        return ApiResponse.ok(responses);
    }
}
