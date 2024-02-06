package com.ukcorp.ieum.api.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ChatGPT 서비스 인터페이스
 *
 * @author : dachan
 * @fileName : ChatGPTServices
 * @since : 01/19/24
 */

@Service
public interface ChatGPTService {

    List<Map<String, Object>> modelList();

    String prompt(String message);

    String prompt(String message, Long careNo);
}