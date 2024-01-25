package com.ukcorp.ieum.event.service;

import com.ukcorp.ieum.event.dto.request.EventInsertRequestDto;
import com.ukcorp.ieum.event.dto.request.EventUpdateRequestDto;
import com.ukcorp.ieum.event.dto.response.EventGetResponseDto;
import com.ukcorp.ieum.event.entity.RegularEvent;
import com.ukcorp.ieum.event.mapper.EventMapper;
import com.ukcorp.ieum.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
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
    public List<EventGetResponseDto> getAllEvent(Long careNo) throws Exception{
        List<RegularEvent> list = eventRepository.findAllByCareInfo_CareNo(careNo);
        if(list == null || list.isEmpty()){
            log.debug("등록된 기념일이 없습니다");
            throw new Exception("등록된 기념일이 없습니다");
        }
        return eventMapper.RegularEventEntityToResponseDto(list);
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
