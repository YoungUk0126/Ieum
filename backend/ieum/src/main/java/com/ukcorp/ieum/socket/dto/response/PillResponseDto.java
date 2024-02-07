package com.ukcorp.ieum.socket.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ukcorp.ieum.socket.dto.PillInfoDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class PillResponseDto implements Response{
    private List<PillInfoDto> contents;
}
