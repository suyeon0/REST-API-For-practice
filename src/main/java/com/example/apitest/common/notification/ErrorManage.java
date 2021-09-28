package com.example.apitest.common.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ErrorManage {

    private final SlackSenderImpl slackSender;
    private final ErrorLogDBImpl errorLogDB;

    /**
     * error 발생시 db에 로그 저장, slack 발송 순차 진행
     *
     * @param msg
     */
    @Transactional(rollbackFor = Exception.class)
    public void manageError(String msg) {
        log.info("*******************manageError**************************");
        errorLogDB.insertErrorLog(msg); // slack 발송 실패해도 db 저장되도록 함
        slackSender.send(msg); //app-slack-info.properties 변경하여 exception 의도적으로 발생시킴
    }
}
