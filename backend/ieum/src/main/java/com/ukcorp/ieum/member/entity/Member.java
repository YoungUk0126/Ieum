package com.ukcorp.ieum.member.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
