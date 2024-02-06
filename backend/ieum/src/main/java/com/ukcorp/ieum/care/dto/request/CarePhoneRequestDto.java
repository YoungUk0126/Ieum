package com.ukcorp.ieum.care.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CarePhoneRequestDto {

    @NotEmpty
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "Invalid phone number format")
    private String phone;
}
