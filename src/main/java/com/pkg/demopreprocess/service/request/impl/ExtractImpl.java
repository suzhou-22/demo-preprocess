package com.pkg.demopreprocess.service.request.impl;

import com.alibaba.fastjson2.JSON;
import com.pkg.demopreprocess.service.request.Extract;
import com.pkg.demopreprocess.service.request.pojo.ProcessedExtractRequest;
import lombok.AllArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


@AllArgsConstructor
public class ExtractImpl implements Extract {
    public static final MediaType MEDIA_TYPE = MediaType.get("application/json");

    private OkHttpClient client;
    private String url;
    private String method;
    private String task;
    private Map<String, String> params;

    @Override
    public String extract(String text) throws IOException {
        ProcessedExtractRequest extractRequest = new ProcessedExtractRequest(text, method, task, params);
        RequestBody requestBody = RequestBody.create(JSON.toJSONBytes(extractRequest), MEDIA_TYPE);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
