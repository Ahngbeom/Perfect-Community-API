package org.zerock.security.detail;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.stereotype.Service;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;
import org.zerock.security.detail.CustomUserDetails;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger log = LogManager.getLogger();

    private final MemberMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserCache userCache = new NullUserCache();
        try {
            log.warn(userCache.getUserFromCache(userName));
            log.warn("Load User By Name:" + userName);
            MemberVO member = mapper.readMember(userName);
            log.warn("Queried By Member Mapper: " + member);
            if (member == null)
                throw new UsernameNotFoundException(userName);
            return new CustomUserDetails(member);
        } catch (UsernameNotFoundException usernameNotFoundException) {
            log.error("\"" + usernameNotFoundException.getMessage() + "\" account does not exist.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
