package com.wanted.market.api.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JoinUserRequest {

    @NotEmpty
    private String loginId;

    @NotEmpty
    @Size(max = 20)
    private String loginPw;
}
