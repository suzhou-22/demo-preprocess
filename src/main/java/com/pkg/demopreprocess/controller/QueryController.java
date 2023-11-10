package com.pkg.demopreprocess.controller;

import com.pkg.demopreprocess.pojo.Message;
import com.pkg.demopreprocess.pojo.Question;
import com.pkg.demopreprocess.service.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class QueryController {

    QueryService queryService;

    @Autowired
    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @PostMapping("queryDatabase")
    @ResponseBody
    List<Message> queryDatabase(@RequestBody Question body) throws IOException {
        log.info(body.toString());
        return queryService.query(body);
    }
}
