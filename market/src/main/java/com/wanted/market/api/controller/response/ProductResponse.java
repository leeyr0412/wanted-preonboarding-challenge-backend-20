package com.wanted.market.api.controller.response;

import com.wanted.market.domain.product.ProductState;
import lombok.Builder;
import lombok.Data;

@Data
public class ProductResponse {

    private Long productId;
    private String productName;
    private Integer productPrice;
    private String state;

    @Builder
    public ProductResponse(Long productId, String productName, Integer productPrice, ProductState state) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.state = state.getText();
    }
}
