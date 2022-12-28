package com.perfect.community.api.controller.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetUserInfoTest extends UserControllerTest {
    @Test
    @DisplayName("[User Info] Valid User")
    void validUserInfo() throws Exception {
        log.warn(getUserInfo("admin"));
    }

    @Test
    @DisplayName("[User Info] Invalid User")
    void invalidUserInfo() throws Exception {
        log.warn(getUserInfo("a"));
    }
}
