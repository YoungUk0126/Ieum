package com.ukcorp.ieum.member.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberDto {
    private String memberId;
    private String password;
    private String name;
    private String phone;
    private String email;
}
