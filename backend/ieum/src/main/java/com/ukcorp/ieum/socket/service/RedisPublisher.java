package com.ukcorp.ieum.socket.service;

import com.ukcorp.ieum.socket.entity.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisPublisher {

    private final RedisTemplate redisTemplate;

    public RedisPublisher(@Qualifier("redisTemplateSocket") RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //    public void publish(ChannelTopic topic, String message) {
    //    redisTemplate.convertAndSend(topic.getTopic(), message);
    //}
    public void publishPojo(ChannelTopic topic, Content content) {
        redisTemplate.convertAndSend(topic.getTopic(), content);
    }
}
