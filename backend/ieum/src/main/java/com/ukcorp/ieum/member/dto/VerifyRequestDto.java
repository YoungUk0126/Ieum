package com.ukcorp.ieum.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class VerifyRequestDto {
    @Email
    @NotEmpty
    private String phone;

    @NotEmpty
    private String code;
}
