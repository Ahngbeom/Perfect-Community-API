package com.perfect.community.api.security.detail;

import com.perfect.community.api.entity.user.UserEntity;
import com.perfect.community.api.mapper.user.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger log = LogManager.getLogger();

    private final UsersMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.warn("Load request by \"" + userName + "\"");
        UserEntity userEntity = mapper.selectUserWithAuthoritiesByUserId(userName);
        log.warn("Load UserEntity By userName: " + userEntity);
        if (userEntity == null)
            throw new UsernameNotFoundException(userName);
        return new CustomUserDetails(userEntity.toDTO());
    }
}
