package com.ukcorp.ieum.event.service;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.care.repository.CareRepository;
import com.ukcorp.ieum.event.dto.request.EventInsertRequestDto;
import com.ukcorp.ieum.event.dto.request.EventUpdateRequestDto;
import com.ukcorp.ieum.event.dto.response.EventGetResponseDto;
import com.ukcorp.ieum.event.entity.RegularEvent;
import com.ukcorp.ieum.event.mapper.EventMapper;
import com.ukcorp.ieum.event.repository.EventRepository;
import com.ukcorp.ieum.jwt.JwtUtil;
import com.ukcorp.ieum.socket.service.SocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    private final SocketService socketService;

    private final EventRepository eventRepository;
    private final CareRepository careRepository;
    private final EventMapper eventMapper;


    @Transactional
    @Override
    public void insertEvent(EventInsertRequestDto event) throws Exception {
        try {
            Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo가 없어요"));
            // 피보호자 정보 확인 및 피보호자 정보 받아오기
            CareInfo care = careRepository.findById(careNo).orElseThrow(() -> new NoSuchElementException("존재하지 않는 피보호자 정보입니다."));
            RegularEvent entity = eventMapper
                    .eventInsertRequestDtoAndCareToRegularEvent(event, care);
            eventRepository.save(entity);

            // socket 전송
            socketService.sendEventDataToIot(careNo);
        } catch (DataIntegrityViolationException e) {
            log.debug("입력 오류!");
            throw new Exception("입력 오류!");
        }
    }

    @Override
    public EventGetResponseDto getEvent(Long eventNo) throws Exception {
        try {
            RegularEvent event = eventRepository.findById(eventNo).orElseThrow(() -> new NoSuchElementException("등록된 기념일이 없습니다"));
            return eventMapper.RegularEventToEventGetResponseDto(event);

        } catch (RuntimeException e) {
            log.debug("조회 오류!");
            throw new Exception("조회 오류!");
        }
    }

    @Override
    public List<EventGetResponseDto> getAllEvent() throws Exception {
        try {
            Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo가 없어요"));
            List<RegularEvent> list = eventRepository.findAllByCareInfo_CareNo(careNo);
            return eventMapper.RegularEventEntityToResponseDto(list);
        } catch (RuntimeException e) {
            log.debug("조회 오류!");
            throw new Exception("조회 오류!");
        }

    }

    @Transactional
    @Override
    public void updateEvent(EventUpdateRequestDto event) throws Exception {
        try {
            Long careNo = JwtUtil.getCareNo().orElseThrow(() -> new Exception("토큰에 CareNo가 없어요"));
            // 피보호자 정보가 있는지 확인 및 피보호자 정보 받아오기
            CareInfo care = careRepository.findById(careNo).orElseThrow(() -> new NoSuchElementException("존재하지 않는 피보호자 정보입니다."));
            // 수정하려는 기념일 정보가 있는지 확인
            if (!eventRepository.existsById(event.getEventNo())) {
                throw new NoSuchElementException("존재하지 않는 기념일 정보입니다.");
            }
            RegularEvent entity = eventMapper
                    .EventUpdateRequestDtoAndCareInfoToRegualrEvent(event, care);
            eventRepository.save(entity);

            // socket 전송
            socketService.sendEventDataToIot(careNo);
        } catch (DataIntegrityViolationException e) {
            log.debug("수정 오류!");
            throw new Exception("수정 오류!");
        }
    }

    @Transactional
    @Override
    public void deleteEvent(Long eventNo) throws Exception {
        try {
            RegularEvent event = eventRepository.findById(eventNo)
                    .orElseThrow(() -> new NoSuchElementException(" NOT FOUND EVENT"));
            eventRepository.deleteById(eventNo);

            // socket 전송
            socketService.sendEventDataToIot(event.getCareInfo().getCareNo());
        } catch (EmptyResultDataAccessException e) {
            log.debug("삭제 오류!");
            throw new Exception("삭제 오류!");
        }

    }
}
