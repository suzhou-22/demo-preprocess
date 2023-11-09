package com.pkg.demopreprocess.service;

import com.pkg.demopreprocess.controller.pojo.ExtractRequest;
import com.pkg.demopreprocess.controller.pojo.Text;

public interface ExtractService {
    static String getText(ExtractRequest extractRequest) {
        Text text = extractRequest.text();

        String textString;
        switch (extractRequest.text().appname()) {
            case Calendar -> {
            }
            case Photo -> {
            }
            case SMS -> {
            }
            case MyDevice -> {
            }
            case Clipboard -> {
            }
            case Contacts -> {
            }
            case Email -> {
            }
            case Memo -> {
                return TextProcessor.memo(text);
            }
            case UserInput -> {
            }
            case GPS -> {
            }
        }

        return "";
    }
}
