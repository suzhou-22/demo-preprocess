package com.pkg.demopreprocess.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.pkg.demopreprocess.pojo.ExtractRequest;
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

    @Override
    public JSONObject extract(ExtractRequest extractRequest) throws IOException {
        String textString = ExtractService.getText(extractRequest);
        String method = extractRequest.method();
        String task = extractRequest.task();
        String time = extractRequest.time();
        Map<String, String> params = extractRequest.params();

        return extract.extract(textString, method, task, params, time);
    }
}
