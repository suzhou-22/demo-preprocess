package com.pkg.demopreprocess.request.impl;

import com.alibaba.fastjson2.JSON;
import com.pkg.demopreprocess.controller.pojo.ExtractRequest;
import com.pkg.demopreprocess.request.Extract;
import com.pkg.demopreprocess.request.RequestProperties;
import com.pkg.demopreprocess.request.pojo.ProcessedExtractRequest;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@ImportResource("classpath:applicationContext.xml")
public class ExtractImpl implements Extract {
    public static final MediaType MEDIA_TYPE = MediaType.get("application/json");

    @Autowired
    private OkHttpClient client;

    @Autowired
    private RequestProperties properties;

    @Override
    public String extract(String text, String method, String task, Map<String, String> params) throws IOException {
        ProcessedExtractRequest extractRequest = new ProcessedExtractRequest(text, method, task, params);
        RequestBody requestBody = RequestBody.create(JSON.toJSONBytes(extractRequest), MEDIA_TYPE);
        Request request = new Request.Builder()
                .url(properties.getUrl())
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
