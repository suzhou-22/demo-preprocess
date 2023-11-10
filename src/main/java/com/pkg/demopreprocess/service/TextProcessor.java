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
}
