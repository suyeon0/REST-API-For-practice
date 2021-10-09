package com.example.apitest.common.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ErrorManage {

    private final SlackSender slackSender;
    private final ErrorLogDB errorLogDB;

    /**
     * error 발생시 db에 로그 저장, slack 발송 순차 진행
     *
     * @param msg
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void manageError(String msg) {
        log.info("*******************manageError**************************");
        errorLogDB.insertErrorLog(msg); // slack 발송 실패하면 log db 저장 안되도록 함
        slackSender.send(msg); //app-slack-info.properties 변경하여 exception 의도적으로 발생시킴
    }
}