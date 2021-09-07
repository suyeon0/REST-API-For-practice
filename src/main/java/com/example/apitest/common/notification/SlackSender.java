package com.example.apitest.common.notification;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.stereotype.Component;

@Component
public class SlackSender {
    public void send(String msg){
        SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
        api.call(new SlackMessage("# 서버-에러-로그", "slack-test", msg));
    }
}
