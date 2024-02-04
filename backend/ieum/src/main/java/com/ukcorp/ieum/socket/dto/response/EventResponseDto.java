package com.ukcorp.ieum.socket.dto.response;

import com.ukcorp.ieum.socket.dto.RegularEventDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDto implements Response{
    List<RegularEventDto> list = new ArrayList<>();
}
