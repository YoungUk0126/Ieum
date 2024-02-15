package com.ukcorp.ieum.message.repository;

import com.ukcorp.ieum.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
  List<Message> findByCareInfoCareNo(Long careNo);

}
