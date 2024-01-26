package com.ukcorp.ieum.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class MemberWithCareNo extends User {

    private final Long careNo;

    public MemberWithCareNo(String username, String password, Long careNo,
                            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.careNo = careNo;
    }
}
