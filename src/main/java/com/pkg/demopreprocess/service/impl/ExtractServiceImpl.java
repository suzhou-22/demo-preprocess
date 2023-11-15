package com.pkg.demopreprocess.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.pkg.demopreprocess.pojo.AppName;
import com.pkg.demopreprocess.pojo.ExtractRequest;
import com.pkg.demopreprocess.pojo.Text;
import com.pkg.demopreprocess.service.ExtractService;
import com.pkg.demopreprocess.service.request.Extract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ExtractServiceImpl implements ExtractService {

    Extract extract;

    @Autowired
    public ExtractServiceImpl(Extract extract) {
        this.extract = extract;
    }

    /**
     * 解析抽取的第一层请求，用ExtractService.getText(text)解析第二层（text）中的请求
     * @param extractRequest 抽取请求
     * @return 发往python后端的请求
     */
    @Override
    public JSONObject extract(ExtractRequest extractRequest) throws IOException {
        Text text = extractRequest.text();
        String method = extractRequest.method();
        String task = extractRequest.task();
        String time = extractRequest.time();
        Map<String, String> params = extractRequest.params();

        String textString = ExtractService.getText(text);

        // 邮件的time要替换成收件时间
        if (text.appname() == AppName.Email) {
            String receivedTime = (String) text.structedContent().get("time");
            receivedTime = receivedTime.replace(" ", "T");
            time = receivedTime;
        }

        return extract.extract(textString, method, task, params, time);
    }
}
