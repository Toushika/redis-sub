package com.toushika.redissub.service;

public interface StudentConsumer {
    void handleMessage(String student);
}
