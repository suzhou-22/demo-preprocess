package com.pkg.demopreprocess.service;

import com.pkg.demopreprocess.pojo.Message;
import com.pkg.demopreprocess.pojo.Question;

import java.io.IOException;
import java.util.List;

public interface QueryService {
    List<Message> query(Question question) throws IOException;
}
