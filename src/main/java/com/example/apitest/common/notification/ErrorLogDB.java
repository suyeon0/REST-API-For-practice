package com.example.apitest.common.notification;

import com.example.apitest.dao.log.LogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ErrorLogDB {

    private final LogMapper logMapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void insertErrorLog(String msg) {
        log.info("###ErrorLogDBImpl insertErrorLog() start");
        logMapper.insertErrorLog(msg);
        log.info("###ErrorLogDBImpl insertErrorLog() end");
    }
}