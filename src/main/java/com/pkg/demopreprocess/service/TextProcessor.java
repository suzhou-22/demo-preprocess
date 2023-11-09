package com.pkg.demopreprocess.service;

import com.pkg.demopreprocess.controller.pojo.Text;

import java.util.Map;

public class TextProcessor {
//    static public String sms(Text text) {
//        Map<String, Object> structedContent = text.structedContent();
//
//    }

    static public String memo(Text text) {
        Map<String, Object> structedContent = text.structedContent();
        String title = (String) structedContent.getOrDefault("title", "");
        String content = (String) structedContent.getOrDefault("content", "");
        return "标题: " + title + ", 内容: " + content;
    }
}
