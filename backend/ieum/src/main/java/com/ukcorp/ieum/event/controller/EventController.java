package com.ukcorp.ieum.event.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @GetMapping
    public ResponseEntity getEventList() {
        // TODO : 기념일 정보 전체 조회 로직 추가
        return new ResponseEntity(HttpStatus.OK);
    }

    // TODO : 기념일 정보 상세 조회
    // TODO : 기념일 정보 등록
    // TODO : 기념일 정보 수정
    // TODO : 기념일 정보 삭제
}
