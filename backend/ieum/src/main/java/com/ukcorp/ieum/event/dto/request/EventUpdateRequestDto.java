package com.ukcorp.ieum.event.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventUpdateRequestDto {
    private Long eventNo;
    private Long careNo;
    private String eventName;
    private LocalDate eventDate;
}
