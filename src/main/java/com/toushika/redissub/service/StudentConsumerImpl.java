package com.toushika.redissub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toushika.redissub.dto.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentConsumerImpl extends AbstractMessageConverter implements StudentConsumer{

    public StudentConsumerImpl(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    public void handleMessage(String message) {

        Student student = getClassObject(message, Student.class);
        log.info("StudentConsumerImpl:: handleMessage:: student {}",student);
    }


}
