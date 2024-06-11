package com.wanted.market.api.controller.request;

import com.wanted.market.api.service.dto.RegisterProductDto;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class RegisterProductRequest {

    @NotBlank(message = "제품 명이 없습니다.")
    @Length(max = 30, message = "제품명은 30자 이내로 입력")
    private String productName;

    @NotNull
    @DecimalMin(value = "10", inclusive = true, message = "제품 가격은 10원 이상 입력 가능합니다.")
    @DecimalMax(value = "10000000000", inclusive = true, message = "최대 가격은 10,000,000,000원 입니다.")
    private Integer price;

    @NotNull
    @DecimalMin(value = "1", inclusive = true, message = "1개 이상의 수량을 등록하세요.")
    @DecimalMax(value = "100000000", inclusive = true, message = "최대 수량은 100,000,000개 입니다.")
    private Integer count;

    public RegisterProductDto toDto(String loginId) {
        return RegisterProductDto.builder()
                .loginId(loginId)
                .count(count)
                .price(price)
                .productName(productName)
                .build();
    }
}
