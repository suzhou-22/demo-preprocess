package com.pkg.demopreprocess.controller;

import com.pkg.demopreprocess.pojo.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MockController {

    @GetMapping("/mock/query/{question}")
    @ResponseBody
    List<Message> query(@PathVariable String question) throws IOException {

        List<Message> rlt = new ArrayList<>();

        rlt.add(new Message(Message.Type.STRING, "", "您的大学同学在杭州的有：", null));

        rlt.add(new Message(Message.Type.PERSON, "刘洋", "刘洋", Map.of(
                "姓名", "刘洋",
                "生日", "1998年11月23日",
                "电话", "176 7345 6789")));

        return rlt;
    }
}
