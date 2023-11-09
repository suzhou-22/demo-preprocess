package com.pkg.demopreprocess.request;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("request")
public class RequestProperties {
    private String url;
}
