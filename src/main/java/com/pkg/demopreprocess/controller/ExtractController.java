package com.pkg.demopreprocess.controller;

import com.pkg.demopreprocess.controller.pojo.ExtractRequest;
import com.pkg.demopreprocess.service.ExtractService;
import com.pkg.demopreprocess.service.request.Extract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class ExtractController {

    @Autowired
    private Extract extract;

    @PostMapping("getExtractionResult")
    @ResponseBody
    Object extract(@RequestBody ExtractRequest extractRequest) throws IOException {
        log.info(String.valueOf(extractRequest));
        String textString = ExtractService.getText(extractRequest);
//        extract.extract(textString);
        return extractRequest;
    }
}
