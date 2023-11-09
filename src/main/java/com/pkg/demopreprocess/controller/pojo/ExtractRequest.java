package com.pkg.demopreprocess.controller.pojo;

import java.util.Map;

public record ExtractRequest(Text text, String method, String task, Map<String, String> params) {
}