package com.example.apitest.common.notification;

import com.example.apitest.common.exception.CustomException;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:app-slack-info.properties")
@RequiredArgsConstructor
@Slf4j
public class SlackSender {

    @Value("${notification.slack.enabled}")
    private boolean slackEnabled;
    @Value("${notification.slack.webhook.url}")
    private String webhookUrl;
    @Value("${notification.slack.channel}")
    private String channel;
    @Value("${notification.slack.botName}")
    private String botName;
    @Value("${notification.slack.icon.emoji}")
    private String iconEmoji;
    @Value("${notification.slack.icon.url}")
    private String iconUrl;

    private final RestTemplateBuilder restTemplateBuilder;

    /**
     * Slack msg 전송 구현
     *
     * @param text
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void send(String text){
        log.info("###SlackSenderImpl send() start");
        if (slackEnabled) {
            try {
                // msg 생성
                RestTemplate restTemplate = restTemplateBuilder.build();
                SlackMsgFormat slackMessage = new SlackMsgFormat(text, channel, botName, iconEmoji, iconUrl);
                String payload = new Gson().toJson(slackMessage);
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

                // 전송
                HttpEntity<String> entity = new HttpEntity<>(payload, headers);
                restTemplate.postForEntity(webhookUrl, entity, String.class); //URI url, @Nullable Object request, Class<T> responseType

            } catch (Exception e) {
                log.error("Unhandled Exception occurred while send slack. [Reason] : ", e);
                throw new CustomException("@transactional test exception 발생");

            }
        }
    }

    /**
     * 슬랙 msg 전송(SlackApi class 사용)
     *
     * @param msg
     */
    public void sendUsingApi(String msg) {
        SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
        api.call(new SlackMessage("# 서버-에러-로그", "slack-test", msg));
    }
}