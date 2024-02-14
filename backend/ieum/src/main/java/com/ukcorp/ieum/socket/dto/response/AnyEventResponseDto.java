package com.ukcorp.ieum.socket.dto.response;

import com.ukcorp.ieum.socket.dto.AnyEventDto;
import com.ukcorp.ieum.socket.dto.RegularEventDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnyEventResponseDto implements Response{
  List<AnyEventDto> list = new ArrayList<>();
}
