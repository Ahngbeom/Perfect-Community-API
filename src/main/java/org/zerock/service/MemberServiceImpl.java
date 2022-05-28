package org.zerock.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.domain.AuthVO;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private static final Logger log = LogManager.getLogger();

    private final MemberMapper mapper;

    private final PasswordEncoder encoder;

    @Override
    public List<MemberVO> getUserList() {
        return (mapper.readAllMember());
    }

    @Override
    public List<AuthVO> getAuthList(String userId) {
        return mapper.readAuthMember(userId);
    }

    @Override
    public boolean createUser(MemberVO member, AuthVO auth) {
        boolean status = false;
        member.setPassword(encoder.encode(member.getPassword()));
        if (mapper.insertMember(member) == 1) {
            if (mapper.insertAuthorityToMember(auth) == 1) {
                status = true;
            }
        }
        return status;
    }

    @Override
    public MemberVO readUser(String userId) {
        return null;
    }

    @Override
    public boolean authorizationToUser(AuthVO auth) {
        return mapper.insertAuthorityToMember(auth) == 1 ? true : false;
    }

    @Override
    public boolean revokeOneAuthorityToUser(AuthVO auth) {
        return mapper.deleteOneAuthorityToMember(auth) == 1 ? true : false;
    }

    @Override
    public boolean revokeAllAuthorityToUser(String userId) {
        return mapper.deleteAllAuthorityToMember((userId)) == 1 ? true : false;
    }

    @Override
    public boolean deleteUser(String userId) {
        if (mapper.deleteMember(userId) == 1)
            return true;
        else {
            log.error("Delete Member Failed");
            return false;
        }
    }

    @Override
    public boolean disableUser(String userId) {
        return mapper.disableMember(userId) == 1 ? true : false;
    }

    @Override
    public boolean enableUser(String userId) {
        return mapper.enableMember(userId) == 1 ? true : false;
    }
}
