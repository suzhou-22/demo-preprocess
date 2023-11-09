package com.pkg.demopreprocess.service.request.pojo;

import java.util.Map;

public record ProcessedExtractRequest(String text, String method, String task, Map<String, String> params) {
}
