package com.ukcorp.ieum.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CheckPhonelRequestDto {
    @NotEmpty
    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "Invalid phone number format")
    String phone;
}
