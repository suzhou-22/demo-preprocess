package com.pkg.demopreprocess.pojo;

import java.util.Map;

public record ExtractRequest(Text text, Map<String, String> params, String method, String task) {
}