package com.pkg.demopreprocess.service.request.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.pkg.demopreprocess.service.request.Extract;
import com.pkg.demopreprocess.pojo.ProcessedExtractRequest;
import lombok.AllArgsConstructor;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;


@AllArgsConstructor
public class ExtractImpl implements Extract {
    public static final MediaType MEDIA_TYPE = MediaType.get("application/json");

    private OkHttpClient client;
    private String url;
    private String endPoint;

    @Override
    public JSONObject extract(String text, String method, String task, Map<String, String> params, String time) throws IOException {
        ProcessedExtractRequest extractRequest = new ProcessedExtractRequest(text, method, task, params, time);
        RequestBody requestBody = RequestBody.create(JSON.toJSONBytes(extractRequest), MEDIA_TYPE);

        Request request = new Request.Builder()
                .url(url + "/" + endPoint)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return JSONObject.parseObject(response.body().string());
        }
    }
}
