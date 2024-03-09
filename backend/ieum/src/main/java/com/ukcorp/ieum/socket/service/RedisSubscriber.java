package com.ukcorp.ieum.socket.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;

    public RedisSubscriber(ObjectMapper objectMapper, @Qualifier("redisTemplateSocket") RedisTemplate redisTemplate) {
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = (String) redisTemplate.getStringSerializer()
                .deserialize(message.getBody());
        try {
            String data = objectMapper.readValue(msg, String.class);
            System.out.println(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
