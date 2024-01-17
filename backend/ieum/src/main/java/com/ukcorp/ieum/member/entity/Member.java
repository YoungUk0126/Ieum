package com.ukcorp.ieum.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    private long memberNo;
    private long careNo;
    private String memberId;
    private String memberPassword;
    private String serialCode;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private boolean withdrawal;
}
