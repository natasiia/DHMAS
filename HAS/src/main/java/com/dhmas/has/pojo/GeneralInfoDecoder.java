package com.dhmas.has.pojo;

import com.alibaba.fastjson.JSON;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;

public class GeneralInfoDecoder implements javax.websocket.Decoder.Text<GeneralInfo>{

    @Override
    public void destroy() {

    }
    @Override
    public void init(EndpointConfig endpointConfig) {

    }
    @Override
    public GeneralInfo decode(String s) throws DecodeException {
        return JSON.parseObject(s,GeneralInfo.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }




}
