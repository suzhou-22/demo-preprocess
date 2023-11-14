package com.pkg.demopreprocess.pojo;

import java.util.Map;

/**
 * 返回给前端，对应前端显示的一条message
 * @param type 消息类型
 * @param display 如果是STRING类型的消息，表示内容；如果是其他类型，表示要显示在按钮上的文字
 * @param attribute 人物和事件的属性，STRING类型的Message为空
 */
public record Message(Type type, String title, String display, Map<String, String> attribute) {
    public enum Type {
        PERSON,
        EVENT,
        STRING
    }


}
