package com.pkg.demopreprocess.controller;

import com.alibaba.fastjson2.JSONObject;
import com.pkg.demopreprocess.pojo.ExtractRequest;
import com.pkg.demopreprocess.service.ExtractService;
import com.pkg.demopreprocess.service.request.Extract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
public class ExtractController {

    private final ExtractService extractService;

    @Autowired
    public ExtractController(ExtractService extractService) {
        this.extractService = extractService;
    }

    @PostMapping("getExtractionResult")
    @ResponseBody
    Object extract(@RequestBody ExtractRequest extractRequest) throws IOException {
        log.info(String.valueOf(extractRequest));

        JSONObject jsonObject = extractService.extract(extractRequest);
        return jsonObject;
    }
}
