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
public class MemberRequestDto {
    @NotEmpty(message = "Member ID cannot be empty")
    private String memberId;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{7,20}$",
            message = "비밀번호는 영문+숫자+특수문자를 포함한 8~20자여야 합니다")
    private String password;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "Invalid phone number format")
    private String phone;

    @Email(message = "Invalid email address")
    private String email;
}
