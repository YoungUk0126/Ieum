package com.ukcorp.ieum.temporalEvent.service;

import com.ukcorp.ieum.temporalEvent.dto.TemporalEventDto;
import java.util.List;

public interface TemporalEventService {
  List<TemporalEventDto> getList(Long careNo);
}
