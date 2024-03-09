package com.ukcorp.ieum.member.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberUpdateDto {
    @NotEmpty(message = "Member ID cannot be empty")
    private String memberId;

    private String password;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "Invalid phone number format")
    private String phone;

    @Email(message = "Invalid email address")
    private String email;
}
