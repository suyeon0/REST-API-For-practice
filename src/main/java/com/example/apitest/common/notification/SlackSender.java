package com.example.apitest.common.notification;

import org.springframework.stereotype.Service;

@Service
public interface SlackSender {
    void send(String text);
}
