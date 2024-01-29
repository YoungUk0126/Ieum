package com.ukcorp.ieum.member.entity;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.member.entity.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MEMBERS")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_NO")
    private Long memberNo;

    @JoinColumn(name = "CARE_NO")
    @OneToOne(fetch = FetchType.LAZY)
    private CareInfo careInfo;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "MEMBER_PASSWORD")
    private String memberPassword;

    @Column(name = "SERIAL_CODE")
    private String serialCode;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "MEMBER_EMAIL")
    private String memberEmail;

    @Column(name = "MEMBER_PHONE")
    private String memberPhone;

    @Column(name = "WITHDRAWAL")
    @Enumerated(EnumType.STRING)
    private Withdrawal withdrawal;

    @Column(name = "role")
    @Convert(converter = StringListConverter.class)
    private List<String> authorities;

    public void setNewMember(CareInfo careInfo) {
        List<String> auth = new ArrayList<>();
        auth.add("ROLE_USER");
        this.authorities = auth;
        this.careInfo = careInfo;
    }

}
