package com.pkg.demopreprocess.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.pkg.demopreprocess.pojo.Message;
import com.pkg.demopreprocess.pojo.Question;
import com.pkg.demopreprocess.service.QueryService;
import com.pkg.demopreprocess.service.request.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class QueryServiceImpl implements QueryService {

    private final Query query;

    @Autowired
    public QueryServiceImpl(Query query) {
        this.query = query;
    }

    private List<Message> convert(JSONObject jsonObject) {
        List<Message> messages = new ArrayList<>();

        JSONArray jsonArray = jsonObject.getJSONArray("answer");

        // 遍历每一项，可能是person、event、三元组、str
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject item = jsonArray.getJSONObject(i);
            // 三元组
            if (item.containsKey("头实体")) {
                String head = item.getString("头实体");
                String tail = item.getString("尾实体");
                String relation = item.getString("关系");
                String display = head + "的" + relation + "是" + tail;
                messages.add(new Message(Message.Type.STRING, display, null));
                continue;
            }

            // str
            if (item.getString("info_type").equals("str") || item.containsKey("text")
                || (item.containsKey("attribute") && item.getJSONObject("attribute").containsKey("text"))) {
                String display = item.getString("text");
                messages.add(new Message(Message.Type.STRING, display, null));
                continue;
            }

            // 人物
            if (item.getString("info_type").equals("人物")) {
                if (item.getString("name").equals("赵飞"))
                    continue;
                String display = (String) item.getOrDefault("name", "");
                // LinkedHashMap 可以保留顺序
                Map<String, String> attribute = new LinkedHashMap<>();

                item.forEach((k, v) -> {
                    if (k.equals("info_type"))
                        return;
                    if (k.equals("name")) {
                        attribute.put("姓名", (String) v);
                        return;
                    }

                    if (v instanceof JSONArray vArray) {
                        StringBuilder sb = new StringBuilder();
                        for (int j = 0; j < vArray.size(); j++) {
                            sb.append(vArray.getString(j));
                            if (j != vArray.size() - 1)
                                sb.append(", ");
                        }
                        attribute.put(k, sb.toString());
                        return;
                    }
                    attribute.put(k, (String) v);
                });

                messages.add(new Message(Message.Type.PERSON, display, attribute));
                continue;
            }

            // TODO：事件
            if (item.getString("info_type").equals("事件")) {
                String display = item.getString("event_name");
                Map<String, String> attribute = new LinkedHashMap<>();

                item.forEach((k, v) -> {
                    if (k.equals("info_type"))
                        return;
                    if (k.equals("event_name")) {
                        attribute.put("事件名", (String) v);
                        return;
                    }

                    // TODO 重复
                    if (v instanceof JSONArray vArray) {
                        StringBuilder sb = new StringBuilder();
                        for (int j = 0; j < vArray.size(); j++) {
                            sb.append(vArray.getString(j));
                            if (j != vArray.size() - 1)
                                sb.append(", ");
                        }
                        attribute.put(k, sb.toString());
                        return;
                    }
                    attribute.put(k, (String) v);
                });

                messages.add(new Message(Message.Type.EVENT, display, attribute));

            }

        }

        return messages;
    }

    @Override
    public List<Message> query(Question question) throws IOException {
        JSONObject jsonObject = this.query.query(question);
        log.info(jsonObject.toJSONString());
        return convert(jsonObject);
    }
}
