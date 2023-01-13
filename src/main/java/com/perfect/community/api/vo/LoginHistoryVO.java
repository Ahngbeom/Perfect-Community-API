package com.perfect.community.api.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginHistoryVO {

    private final String user_id;
    private final String ip_address;
    private final String user_agent;

    @Builder
    public LoginHistoryVO(String user_id, String ip_address, String user_agent) {
        this.user_id = user_id;
        this.ip_address = ip_address;
        this.user_agent = user_agent;
    }
}
