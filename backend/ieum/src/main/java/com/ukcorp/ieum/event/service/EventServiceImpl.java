package com.ukcorp.ieum.event.service;

import com.ukcorp.ieum.event.dto.request.EventInsertRequestDto;
import com.ukcorp.ieum.event.dto.request.EventUpdateRequestDto;
import com.ukcorp.ieum.event.dto.response.EventGetResponseDto;
import com.ukcorp.ieum.event.entity.RegularEvent;
import com.ukcorp.ieum.event.mapper.EventMapper;
import com.ukcorp.ieum.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;


    @Transactional
    @Override
    public void insertEvent(EventInsertRequestDto event) {

    }

    @Override
    public EventGetResponseDto getEvent(Long eventNo) throws Exception{
        Optional<RegularEvent> tempEvent = eventRepository.findById(eventNo);
        if(tempEvent.isPresent()) {
            RegularEvent event = tempEvent.get();
            EventGetResponseDto eventGetResponseDto = eventMapper.RegularEventToEventGetResponseDto(event);
            return eventGetResponseDto;
        } else {
            throw new Exception("사라진 기념일 정보입니다.");
        }
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
