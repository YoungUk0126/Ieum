package com.ukcorp.ieum.chat.repository;

import com.ukcorp.ieum.chat.entity.ChatHistory;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

  Page<ChatHistory> findAllByCareInfoCareNoOrderByChatHistoryNoAsc(Long careNo, Pageable pageable);
  Page<ChatHistory> findAllByCareInfoCareNoAndChatDateBetweenOrderByChatHistoryNoAsc(Long careNo, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

  List<ChatHistory> findTop8ByCareInfoCareNo(Long careNo);

  ChatHistory findFirstByCareInfoCareNoOrderByChatDateDesc(Long careNo);
}