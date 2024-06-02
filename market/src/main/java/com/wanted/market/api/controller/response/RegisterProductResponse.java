package com.wanted.market.api.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
public class RegisterProductResponse {
    private Long productId;
    private String productName;
    private Integer productPrice;
    private Integer productCount;

    @Builder
    private RegisterProductResponse(Long productId, String productName, Integer productPrice, Integer productCount) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
    }
}
