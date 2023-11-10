package com.pkg.demopreprocess.service;

import com.alibaba.fastjson2.JSONObject;
import com.pkg.demopreprocess.pojo.ExtractRequest;
import com.pkg.demopreprocess.pojo.Text;

import java.io.IOException;

public interface ExtractService {


    static String getText(ExtractRequest extractRequest) {
        Text text = extractRequest.text();

        return switch (extractRequest.text().appname()) {
            case Calendar -> TextProcessor.calendar(text);
            case Photo -> "";
            case SMS -> TextProcessor.sms(text);
            case MyDevice -> "";
            case Clipboard -> TextProcessor.clipboard(text);
            case Contacts -> "";
            case Email -> TextProcessor.email(text);
            case Memo -> TextProcessor.memo(text);
            case UserInput -> "";
            case GPS -> "";
        };
    }

    JSONObject extract(ExtractRequest extractRequest) throws IOException;
}
