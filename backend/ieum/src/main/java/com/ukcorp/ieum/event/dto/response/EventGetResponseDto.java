package com.ukcorp.ieum.event.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventGetResponseDto {
    private Long eventNo;
    private Long careNo;
    private String eventName;
    private LocalDate eventDate;
}
