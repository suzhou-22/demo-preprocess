package com.pkg.demopreprocess.factorybean;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.FactoryBean;

import java.util.concurrent.TimeUnit;

public class ClientFactoryBean implements FactoryBean<OkHttpClient> {
    @Override
    public OkHttpClient getObject() throws Exception {
        return new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public Class<?> getObjectType() {
        return OkHttpClient.class;
    }
}
