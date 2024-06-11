package com.wanted.market.api.service;

import com.wanted.market.api.controller.response.ProductResponse;
import com.wanted.market.api.controller.response.RegisterProductResponse;
import com.wanted.market.api.service.dto.RegisterProductDto;
import com.wanted.market.domain.product.Product;
import com.wanted.market.domain.product.ProductQueryRepository;
import com.wanted.market.domain.product.ProductRepository;
import com.wanted.market.domain.product.ProductState;
import com.wanted.market.domain.users.Users;
import com.wanted.market.domain.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;
    private final UsersRepository usersRepository;

    public RegisterProductResponse registerProduct(RegisterProductDto dto) {

        Users findUser = usersRepository.findByLoginId(dto.getLoginId()).orElseThrow(() ->
                new NoSuchElementException("유저 정보를 찾을 수 없음"));

        Product savedProduct = getSavedProduct(dto, findUser);
        return RegisterProductResponse.builder()
                .productId(savedProduct.getId())
                .productName(savedProduct.getProductName())
                .productPrice(savedProduct.getPrice())
                .productCount(savedProduct.getCount())
                .build();
    }

    public List<ProductResponse> getAllProducts() {
        return productQueryRepository.getAllProducts();
    }

    private Product getSavedProduct(RegisterProductDto dto, Users findUser) {
        Product product = Product.builder()
                .seller(findUser)
                .productName(dto.getProductName())
                .price(dto.getPrice())
                .state(ProductState.AVAILABLE)
                .count(dto.getCount())
                .build();
        return productRepository.save(product);
    }
}
