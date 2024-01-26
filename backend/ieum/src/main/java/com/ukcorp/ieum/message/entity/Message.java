package com.ukcorp.ieum.message.entity;

import com.ukcorp.ieum.care.entity.CareInfo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MESSAGE")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MESSAGE_NO")
  private Long messageNo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CARE_NO")
  private CareInfo careInfo;

  @Column(name = "MESSAGE_NAME")
  private String messageName;

  @Column(name = "MESSAGE_PEOPLE")
  private String messagePeople;

  @Column(name = "MESSAGE_TYPE")
  private String messageType;

  @Column(name = "MESSAGE_TIME")
  private LocalDate messageTime;

}
