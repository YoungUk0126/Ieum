package com.ukcorp.ieum.temporalEvent.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TemporalEventUpdateRequestDto {

  private Long eventNo;

  private String eventName;

  @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime eventDate;
}

