package com.ukcorp.ieum.event.service;

import com.ukcorp.ieum.event.dto.request.EventInsertRequestDto;
import com.ukcorp.ieum.event.dto.request.EventUpdateRequestDto;
import com.ukcorp.ieum.event.dto.response.EventGetResponseDto;
import com.ukcorp.ieum.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;


    @Transactional
    @Override
    public void insertEvent(EventInsertRequestDto event) {

    }

    @Override
    public EventGetResponseDto getEvent(Long eventNo) {
        return null;
    }

    @Override
    public List<EventGetResponseDto> getAllEvent(Long careNo) {
        return null;
    }

    @Transactional
    @Override
    public void updateEvent(EventUpdateRequestDto event) {

    }

    @Transactional
    @Override
    public void deleteEvent(Long event) {

    }
}
