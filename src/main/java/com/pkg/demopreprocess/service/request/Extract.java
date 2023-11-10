package com.pkg.demopreprocess.service.request;

import com.alibaba.fastjson2.JSONObject;

import java.io.IOException;
import java.util.Map;

public interface Extract {
    JSONObject extract(String text, String method, String task, Map<String, String> params, String time) throws IOException;
}
