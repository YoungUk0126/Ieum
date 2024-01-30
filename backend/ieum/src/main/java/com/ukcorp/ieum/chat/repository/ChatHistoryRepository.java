package com.ukcorp.ieum.chat.repository;

import com.ukcorp.ieum.chat.entity.ChatHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChatHistoryRepository extends JpaRepository<Long, ChatHistory> {

  List<ChatHistory> findAllByCareOrderByChatHistoryNoASC(Pageable pageable);
  List<ChatHistory> findAllByCareChatDateBetweenOrderByChatHistoryNoASC(Pageable pageable, LocalDate startDate, LocalDate endDate);
}