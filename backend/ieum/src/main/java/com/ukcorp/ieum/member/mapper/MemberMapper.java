package com.ukcorp.ieum.member.mapper;

import com.ukcorp.ieum.member.dto.MemberDto;
import com.ukcorp.ieum.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target = "memberName", source = "name")
    @Mapping(target = "memberPassword", source = "password", qualifiedByName = "encryptPassword")
    @Mapping(target = "memberEmail", source = "email")
    @Mapping(target = "memberPhone", source = "phone")
    @Mapping(target = "memberNo", ignore = true)
    @Mapping(target = "careInfo", ignore = true)
    @Mapping(target = "serialCode", ignore = true)
    @Mapping(target = "withdrawal", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Member memberDtoToMember(MemberDto member);

    @Named("encryptPassword")
    default String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
