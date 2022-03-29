package com.toushika.redissub.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractMessageConverter {

    private final ObjectMapper objectMapper;

    protected  <T> T getClassObject(String s, Class<T> aClass) {
        try {
            return objectMapper.readValue(s,aClass);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
