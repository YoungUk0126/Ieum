package com.ukcorp.ieum.chat.entity;

import com.ukcorp.ieum.care.entity.CareInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "CHAT_HISTORY")
public class ChatHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CHAT_HISTORY_NO")
  private Long chatHistoryNo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CARE_NO")
  private CareInfo careInfo;

  @Lob
  @Column(name = "MESSAGE_CONTENT")
  private String messageContent;

  @Column(name = "SPEAKER")
  @Enumerated(EnumType.STRING)
  private Subject speaker;

  @Column(name = "LISTENER")
  @Enumerated(EnumType.STRING)
  private Subject listener;

  @Column(name = "EMOTION")
  private String emotion;

  @Column(name = "CAHT_DATE")
  @CreationTimestamp
  private LocalDateTime chatDate;

}
