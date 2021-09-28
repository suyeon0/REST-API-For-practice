package com.example.apitest.common.notification;

import com.example.apitest.dao.LogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ErrorLogDBImpl implements ErrorLogDB{

    private final LogMapper logMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void insertErrorLog(String msg) {
        log.info("###ErrorLogDBImpl insertErrorLog() start");
        logMapper.insertErrorLog(msg);
        log.info("###ErrorLogDBImpl insertErrorLog() end");
    }
}
