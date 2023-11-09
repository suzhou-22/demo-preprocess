package com.pkg.demopreprocess.controller.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record Text(@JsonProperty("user_id") String userId,
                   AppName appname,
                   @JsonProperty("operation_type") OperationType operationType,
                   @JsonProperty("info_id") String infoId,
                   @JsonProperty("structed_content") Map<String, Object> structedContent) {
}
