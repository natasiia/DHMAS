package com.dhmas.has.pojo;

import com.alibaba.fastjson.JSON;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;

public class StatusLocalisDecoder implements javax.websocket.Decoder.Text<StatusLocalis>{
    @Override
    public StatusLocalis decode(String s) throws DecodeException {
        return JSON.parseObject(s,StatusLocalis.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
