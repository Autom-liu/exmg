package com.edu.exmg.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author autom
 * @date 2021-04-20
 **/
@Data
public class WxUserSession {

    @JsonProperty("session_key")
    private String sessionKey;

    private String openid;

    private String unionid;

    private String errcode;

    private String errmsg;

}
