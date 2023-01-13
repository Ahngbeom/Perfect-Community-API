package com.perfect.community.api.mapper.user;

import com.perfect.community.api.vo.LoginHistoryVO;
import org.springframework.stereotype.Component;

@Component
public interface LoginHistoryMapper {
    int saveLoggedInLog(LoginHistoryVO loginHistoryVO);
}
