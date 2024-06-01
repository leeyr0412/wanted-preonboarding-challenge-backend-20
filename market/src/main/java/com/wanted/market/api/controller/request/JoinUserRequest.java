package com.wanted.market.api.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class JoinUserRequest {

    @NotEmpty
    private String loginId;

    @NotEmpty
    @Length(max = 20)
    private String loginPw;
}
