package com.wanted.market.domain.product;

import lombok.Getter;

@Getter
public enum ProductState {

    AVAILABLE("판매중"), RESERVED("예약중"), COMPLETED("완료");

    private final String text;

    ProductState(String text) {
        this.text = text;
    }
}
