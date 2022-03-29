package com.toushika.redissub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisConsumer implements MessageListener {

    private final ObjectMapper objectMapper;
    private final StudentConsumer studentConsumer;

    @Override
    public void onMessage(Message message, byte[] pattern) {

        try {
            var msg = objectMapper.readValue(message.getBody(),String.class);
            log.info("RedisConsumer:: onMessage:: message: {}", msg);

            studentConsumer.handleMessage(msg);

        } catch (IOException e) {
            log.info("RedisConsumer:: onMessage:: ERROR: {}", e);
        }
    }
}
