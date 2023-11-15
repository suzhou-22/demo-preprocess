package com.pkg.demopreprocess.pojo.frontend;

import com.pkg.demopreprocess.pojo.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * 要返回给前端的Event
 * 前端需要时间
 * 如果是时间点，endTimestamp就为0
 */
@Data
@AllArgsConstructor
public class Event {
    String title;
    String display;
    long startTimestamp;
    long endTimestamp;
    Map<String, String> attribute;

    public Event(Message message) {
        this.title = message.title();
        this.display = message.display();
        this.startTimestamp = getStartTimestamp(message);
        this.endTimestamp = 0;
    }

    static long getStartTimestamp(Message message) {
        return System.currentTimeMillis();
    }
}
