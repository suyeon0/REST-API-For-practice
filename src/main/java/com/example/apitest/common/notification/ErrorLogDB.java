package com.example.apitest.common.notification;

import org.springframework.stereotype.Service;

@Service
public interface ErrorLogDB {
    void insertErrorLog(String msg);
}
