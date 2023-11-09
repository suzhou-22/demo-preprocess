package com.pkg.demopreprocess.request;

import java.io.IOException;
import java.util.Map;

public interface Extract {
    String extract(String text, String method, String task, Map<String, String> params) throws IOException;
}
