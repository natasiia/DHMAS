package com.dhmas.has.pojo;

import com.alibaba.fastjson.JSON;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class GeneralInfoEncoder implements Encoder.Text<GeneralInfo>{
    @Override
    public String encode(GeneralInfo info) throws EncodeException {
        return JSON.toJSONString(info);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
