package com.ukcorp.ieum.pill.entity;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PILL_INFO")
public class PillInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PILL_INFO_NO")
    private Long pillInfoNo;

//    ManyToOne 은 default 가 eager여서 Lazy로 해야함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARE_NO")
    private CareInfo careInfo;

    @Column(name = "PILL_NAME", nullable = false)
    private String pillName;

    @Column(name = "PILL_START_DATE", nullable = false)
    private LocalDate pillStartDate;

    @Column(name = "PILL_END_DATE", nullable = false)
    private LocalDate pillEndDate;

    //    식전/식후
    @Column(name = "PILL_METHOD", nullable = false)
    @Enumerated(EnumType.STRING)
    private PillMethod pillMethod;

    @Column(name = "PILL_DATE", nullable = false)
    private String pillDate;

//    조회 목적으로 있는 필드이므로 PillInfo Entity를 만들 때 딱히 안넣어줘도 됨
//    cascade = CascadeType.ALL 라는 키워드로 실제 DB에 적용하게끔 할 수 있대 - 김규현
//    난 이해도가 부족해서 쓰지말래
    @OneToMany(mappedBy = "pillInfo", fetch = FetchType.LAZY)
    private List<PillTime> pillTimes;
}
