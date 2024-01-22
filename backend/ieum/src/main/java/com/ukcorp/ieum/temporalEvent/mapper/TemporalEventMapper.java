package com.ukcorp.ieum.temporalEvent.mapper;
import com.ukcorp.ieum.temporalEvent.dto.TemporalEventDto;
import com.ukcorp.ieum.temporalEvent.entity.TemporalEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class TemporalEventMapper {
  private final ModelMapper modelMapper;

  public TemporalEventDto convertToDto(TemporalEvent entity) {
    return modelMapper.map(entity, TemporalEventDto.class);
  }
}
