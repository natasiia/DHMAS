package com.dhmas.has.pojo;

import com.alibaba.fastjson.JSON;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class StatusLocalisEncoder implements Encoder.Text<StatusLocalis>{
    @Override
    public String encode(StatusLocalis statusLocalis) throws EncodeException {
        return JSON.toJSONString(statusLocalis);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
