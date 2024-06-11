package com.wanted.market.domain.transaction;

import lombok.Getter;

@Getter
public enum TransactionState {

    PENDING_APPROVAL("승인대기"), COMPLETED("완료");

    private final String text;

    TransactionState(String text) {
        this.text = text;
    }
}
