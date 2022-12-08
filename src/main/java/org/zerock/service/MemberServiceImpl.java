package org.zerock.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.DTO.AuthorityDTO;
import org.zerock.DTO.UserDTO;
import org.zerock.mapper.MemberMapper;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private static final Logger log = LogManager.getLogger();

    private final MemberMapper mapper;

    private final PasswordEncoder encoder;

    @Override
    public List<UserDTO> getUserList() {
        return (mapper.readAllMember());
    }

    @Override
    public List<AuthorityDTO> getAuthList(String userId) {
        log.info(mapper.readAuthMember(userId));
        return mapper.readAuthMember(userId);
    }

    @Override
    public void createUser(UserDTO user) throws RuntimeException {
        user.setPassword(encoder.encode(user.getPassword()));
        if (mapper.insertMember(user) == 1) {
            for (AuthorityDTO auth : user.getAuthList()) {
                auth.setUserId(user.getUserId());
                if (!authorizationToUser(auth))
                    throw new RuntimeException("Authorization failure");
            }
        } else {
            throw new RuntimeException("Create user failure");
        }
    }

    @Override
    public UserDTO readUserById(String userId) {
        return mapper.readMemberByUserId(userId);
    }

    @Override
    public UserDTO readUserByName(String userName) {
        return mapper.readMemberByUserName(userName);
    }

    @Override
    public boolean authorizationToUser(AuthorityDTO auth) {
        return mapper.insertAuthorityToMember(auth) == 1;
    }

    @Override
    public boolean revokeOneAuthorityToUser(AuthorityDTO auth) {
        return mapper.deleteOneAuthorityToMember(auth) == 1;
    }

    @Override
    public boolean revokeAllAuthorityToUser(String userId) {
        return mapper.deleteAllAuthorityToMember((userId)) >= 0;
    }

    @Override
    public void deleteUser(String userId) {
        if (revokeAllAuthorityToUser(userId)) {
            if (mapper.deleteMember(userId)== 0)
                throw new RuntimeException("That user doesn't exist.");
        }
    }

    @Override
    public boolean disableUser(String userId) {
        return mapper.disableMember(userId) == 1;
    }

    @Override
    public boolean enableUser(String userId) {
        return mapper.enableMember(userId) == 1;
    }

    @Override
    public boolean hasAdminRole(String userId) {
        AtomicBoolean result = new AtomicBoolean(false);
        readUserById(userId).getAuthList().forEach(auth -> {
            if (auth.equals("ROLE_ADMIN"))
                result.set(true);
        });
        return result.get();
    }

    @Override
    public UserDTO dtoConverter(UserDetails userDetails) {
        return null;
    }
}
