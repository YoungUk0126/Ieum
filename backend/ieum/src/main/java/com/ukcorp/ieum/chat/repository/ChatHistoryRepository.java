package com.ukcorp.ieum.chat.repository;

import com.ukcorp.ieum.care.entity.CareInfo;
import com.ukcorp.ieum.chat.entity.ChatHistory;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

  List<ChatHistory> findAllByCareInfoCareNoOrderByChatHistoryNoAsc(CareInfo careInfo);
  List<ChatHistory> findAllByCareInfoCareNoAndChatDateBetweenOrderByChatHistoryNoAsc(Long careNo, LocalDate startDate, LocalDate endDate, Pageable pageable);

}