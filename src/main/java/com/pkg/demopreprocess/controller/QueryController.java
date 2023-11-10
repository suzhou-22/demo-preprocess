package com.pkg.demopreprocess.controller;

import com.pkg.demopreprocess.pojo.Message;
import com.pkg.demopreprocess.pojo.Question;
import com.pkg.demopreprocess.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

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
        return queryService.query(body);
    }
}
