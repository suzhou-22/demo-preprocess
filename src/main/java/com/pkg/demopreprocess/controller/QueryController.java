package com.pkg.demopreprocess.controller;

import com.pkg.demopreprocess.pojo.Message;
import com.pkg.demopreprocess.pojo.Question;
import com.pkg.demopreprocess.pojo.frontend.Event;
import com.pkg.demopreprocess.service.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
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

    @GetMapping("query/timeline")
    @ResponseBody
    List<Event> timeline() {
        List<Event> messages = new ArrayList<>();

        messages.add(new Event(new Message(Message.Type.EVENT, "家庭活动", "家长会\n科技馆\n家庭聚会", null)));
        messages.add(new Event(new Message(Message.Type.EVENT, "空闲", "", null)));
        messages.add(new Event(new Message(Message.Type.EVENT, "会议", "团队会议\n客户会议", null)));
        messages.add(new Event(new Message(Message.Type.EVENT, "出差", "杭州出差", null)));

        return messages;
    }
}
