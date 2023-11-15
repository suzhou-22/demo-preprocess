package com.pkg.demopreprocess.service;

import com.pkg.demopreprocess.pojo.Text;

import java.util.Map;
import java.util.StringJoiner;

public class TextProcessor {

    public static final int MESSAGE_TYPE_ALL = 0;
    public static final int MESSAGE_TYPE_INBOX = 1;
    public static final int MESSAGE_TYPE_SENT = 2;
    public static final int MESSAGE_TYPE_DRAFT = 3;
    public static final int MESSAGE_TYPE_OUTBOX = 4;
    public static final int MESSAGE_TYPE_FAILED = 5; // for failed outgoing messages
    public static final int MESSAGE_TYPE_QUEUED = 6; // for messages to send later

    private static class Formatter {
        StringJoiner fields = new StringJoiner(", ");

        Formatter add(String key, String value) {
            if (value != null && !value.isBlank()) {
                fields.add(key + ": " + value);
            }
            return this;
        }

        @Override
        public String toString() {
            return fields.toString();
        }
    }

    static public String sms(Text text) {
        Map<String, Object> structedContent = text.structedContent();
        Formatter formatter = new Formatter();

        String phoneNumber = (String) structedContent.get("phone number");
        String phoneName = (String) structedContent.get("phone name");
        String contents = (String) structedContent.get("contents");
        String time = (String) structedContent.get("time");
        int type = (int) structedContent.get("type");  // TODO: 只考虑了接受短信的情况

        if (type == MESSAGE_TYPE_SENT) {
            formatter.add("收信人", phoneName)
                    .add("收信人号码", phoneNumber)
                    .add("发信人", "杨梓")  // TODO delete this line!
                    .add("内容", contents)
                    .add("时间", time);
            return formatter.toString();
        }

        formatter.add("发信人", phoneName)
                .add("发信人号码", phoneNumber)
                .add("收信人", "杨梓")  // TODO delete this line!
                .add("内容", contents)
                .add("时间", time);
        return formatter.toString();
    }

    public static String memo(Text text) {
        Map<String, Object> structedContent = text.structedContent();
        String title = (String) structedContent.getOrDefault("title", "");
        String content = (String) structedContent.getOrDefault("content", "");

        Formatter formatter = new Formatter();
        formatter.add("标题", title)
                .add("内容", content);

        return formatter.toString();
    }

    public static String clipboard(Text text) {
        return (String) text.structedContent().get("content");

    }

    public static String email(Text text) {
        Map<String, Object> structedContent = text.structedContent();

        Formatter formatter = new Formatter();

        String subject = (String) structedContent.get("subject");
        String sender = (String) structedContent.get("sender");
        String recipient = (String) structedContent.get("recipient");
        String ccAddress = (String) structedContent.get("ccAddress"); // TODO
        String content = (String) structedContent.get("content");

        formatter.add("发件人", sender)
                .add("收件人", recipient)
                .add("主题", subject)
                .add("内容", content);
        return formatter.toString();
    }

    public static String calendar(Text text) {
        Map<String, Object> structedContent = text.structedContent();
        Formatter formatter = new Formatter();

        String title = (String) structedContent.get("title");
        String description = (String) structedContent.get("description");
        String location = (String) structedContent.get("location");
        String startTime = (String) structedContent.get("startTime");
        String endTime = (String) structedContent.get("endTime");


        formatter.add("标题", title)
                .add("表述", description)
                .add("开始时间", startTime)
                .add("结束时间", endTime)
                .add("地点", location);

        return formatter.toString();
    }
}
