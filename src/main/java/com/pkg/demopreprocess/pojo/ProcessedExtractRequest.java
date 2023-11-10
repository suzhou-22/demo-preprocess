package com.pkg.demopreprocess.pojo;

import java.util.Map;

/**
 * 和run.py接受的post body格式一样
 */
public record ProcessedExtractRequest(String text, String method, String task, Map<String, String> params, String time) {
}
