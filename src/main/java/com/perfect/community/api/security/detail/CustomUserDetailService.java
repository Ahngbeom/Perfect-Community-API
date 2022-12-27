package com.perfect.community.api.security.detail;

import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.entity.user.UserEntity;
import com.perfect.community.api.mapper.user.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger log = LogManager.getLogger();

    private final UsersMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserCache userCache = new NullUserCache();
        try {
            log.info("UserCache: " + userCache.getUserFromCache(userName));
//            log.warn("Load User By Name:" + userName);
            UserDTO user = mapper.selectUserWithAuthoritiesByUserId(userName).toDTO();
            log.info("Load UserDTO By userName: " + user);
            if (user == null)
                throw new UsernameNotFoundException(userName);
            return new CustomUserDetails(user);
        } catch (UsernameNotFoundException usernameNotFoundException) {
            log.error("\"" + usernameNotFoundException.getMessage() + "\" account does not exist.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
