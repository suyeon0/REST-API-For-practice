package com.example.apitest.common.notification;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.ObjectUtils;

public class SlackMsgFormat {

    @SerializedName("text")
    private String text;

    @SerializedName("channel")
    private String channel;

    @SerializedName("username")
    private String botName;

    @SerializedName("icon_emoji")
    private String iconEmoji;

    @SerializedName("icon_url")
    private String iconUrl;

    public SlackMsgFormat(String text, String channel, String botName, String iconEmoji, String iconUrl) {
        this.text = text;
        this.channel = channel;
        this.botName = botName;

        if (ObjectUtils.isNotEmpty(iconEmoji)) {
            this.iconEmoji = iconEmoji;
        }
        if (ObjectUtils.isNotEmpty(iconUrl)) {
            this.iconUrl = iconUrl;
        }
    }

}