package com.pkg.demopreprocess.service.request;

import com.alibaba.fastjson2.JSONObject;
import com.pkg.demopreprocess.pojo.Question;

import java.io.IOException;

public interface Query {
    JSONObject query(Question question) throws IOException;
}
