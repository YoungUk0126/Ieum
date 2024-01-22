package com.ukcorp.ieum.schedule.service;

import com.ukcorp.ieum.schedule.dto.TemporalEventDto;
import java.util.List;

public interface TemporalEventService {
  List<TemporalEventDto> getList(Long careNo);
}
