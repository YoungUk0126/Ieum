package com.ukcorp.ieum.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class MemberResponseDto {
    private String memberId;
    private String name;
    private String phone;
    private String email;
}
