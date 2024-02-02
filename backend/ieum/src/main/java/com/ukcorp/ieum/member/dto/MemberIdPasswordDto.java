package com.ukcorp.ieum.member.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MemberIdPasswordDto {
    @NotEmpty
    String memberId;

    @NotEmpty(message = "Password cannot be empty")
    String password;
}
