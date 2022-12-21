package com.board.api.security.detail;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.stereotype.Service;
import com.board.api.dto.user.UserDTO;
import com.board.api.mapper.user.UserMapper;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger log = LogManager.getLogger();

    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserCache userCache = new NullUserCache();
        try {
            log.info("UserCache: " + userCache.getUserFromCache(userName));
//            log.warn("Load User By Name:" + userName);
            UserDTO user = mapper.readMemberByUserId(userName);
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
