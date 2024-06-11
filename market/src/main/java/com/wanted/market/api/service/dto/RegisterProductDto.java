package com.wanted.market.api.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RegisterProductDto {

    private String loginId;
    private String productName;
    private Integer price;
    private Integer count;

    @Builder
    private RegisterProductDto(String loginId, String productName, Integer price, Integer count) {
        this.loginId = loginId;
        this.productName = productName;
        this.price = price;
        this.count = count;
    }
}
