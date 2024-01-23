package com.ukcorp.ieum.member.mapper;

import com.ukcorp.ieum.member.dto.MemberDto;
import com.ukcorp.ieum.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target = "memberName", source = "name")
    @Mapping(target = "memberPassword", source = "password")
    @Mapping(target = "memberEmail", source = "email")
    @Mapping(target = "memberPhone", source = "phone")
    @Mapping(target = "memberNo", ignore = true)
    @Mapping(target = "careInfo", ignore = true)
    @Mapping(target = "serialCode", ignore = true)
    @Mapping(target = "withdrawal", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Member memberDtoToMember(MemberDto member);
}
