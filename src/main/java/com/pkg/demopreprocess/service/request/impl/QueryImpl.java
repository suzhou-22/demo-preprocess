package com.pkg.demopreprocess.service.request.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.pkg.demopreprocess.pojo.Question;
import com.pkg.demopreprocess.service.request.Query;
import lombok.AllArgsConstructor;
import okhttp3.*;

import java.io.IOException;

@AllArgsConstructor
public class QueryImpl implements Query {
    public static final MediaType MEDIA_TYPE = MediaType.get("application/json");

    OkHttpClient client;
    String host;
    String endpoint;

    @Override
    public JSONObject query(Question question) throws IOException {
        String url = host + "/" + endpoint;
        RequestBody requestBody = RequestBody.create(JSON.toJSONBytes(question), MEDIA_TYPE);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return JSON.parseObject(response.body().string());
        }
    }
}
