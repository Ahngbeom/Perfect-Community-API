package org.zerock.security;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger log = LogManager.getLogger();

    private final MemberMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        log.warn("Load User By Name:" + userName);
        MemberVO member = mapper.readMember(userName);
        log.warn("Queried By Member Mapper: " + member);

        if (member == null)
            throw new UsernameNotFoundException(userName);
        return new CustomUser(member);
    }

}
