package com.pkg.demopreprocess.service;

import com.pkg.demopreprocess.pojo.Text;

import java.util.Map;

public class TextProcessor {
    static public String sms(Text text) {
        Map<String, Object> structedContent = text.structedContent();
        String phoneNumber = (String) structedContent.get("phone number");
        String phoneName = (String) structedContent.get("phone name");
        String contents = (String) structedContent.get("contents");
        String time = (String) structedContent.get("time");
        int type = (int) structedContent.get("type");  // TODO: 只考虑了接受短信的情况

        return "发信人: " + phoneName +
                ", 发信人号码: " + phoneNumber +
                ", 内容: " + contents +
                ", 时间: " + time;
    }

    public static String memo(Text text) {
        Map<String, Object> structedContent = text.structedContent();
        String title = (String) structedContent.getOrDefault("title", "");
        String content = (String) structedContent.getOrDefault("content", "");
        return "标题: " + title + ", 内容: " + content;
    }

    public static String clipboard(Text text) {
        return (String) text.structedContent().get("content");

    }

    public static String email(Text text) {
        Map<String, Object> structedContent = text.structedContent();
        StringBuilder sb = new StringBuilder();

        String subject = (String) structedContent.get("subject");
        String time = (String) structedContent.get("time");
        String sender = (String) structedContent.get("sender");
        String recipient = (String) structedContent.get("recipient");
        String ccAddress = (String) structedContent.get("ccAddress"); // TODO
        String content = (String) structedContent.get("content");

        sb.append("发件人: ").append(sender)
                .append(", 收件人: ").append(recipient)
                .append(", 主题: ").append(subject)
                .append(", 内容: ").append(content)
                .append(", 时间： ").append(time);
        return sb.toString();
    }

    public static String calendar(Text text) {
        Map<String, Object> structedContent = text.structedContent();
        StringBuilder sb = new StringBuilder();

        String title = (String) structedContent.get("title");
        String description = (String) structedContent.get("description");
        String location = (String) structedContent.get("location");
        String startTime = (String) structedContent.get("startTime");
        String endTime = (String) structedContent.get("endTime");


        sb.append("标题: ").append(title).append(", 表述").append(description);
        sb.append(", 开始时间: ").append(startTime).append(", 结束时间: ").append(endTime);
        if (location != null && !location.isBlank()) {
            sb.append(", 地点: ").append(location);
        }

        return sb.toString();
    }
}
