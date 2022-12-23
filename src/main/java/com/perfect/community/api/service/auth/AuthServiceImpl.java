package com.perfect.community.api.service.auth;

import com.perfect.community.api.dto.auth.AuthorityDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.perfect.community.api.mapper.auth.AuthMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LogManager.getLogger();

    private final AuthMapper authMapper;
//    private final UserService userService;

//    private final PasswordEncoder encoder;

    @Override
    public List<AuthorityDTO> getAuthList(String userId) {
        return authMapper.readAuthMember(userId);
    }

    @Override
    public void grantAuthority(AuthorityDTO auth) {
        try {
            if (authMapper.insertAuthorityToMember(auth) != 1)
                throw new RuntimeException("Failed to grant user authority");
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new DuplicateKeyException("Duplicate authority");
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new DataIntegrityViolationException("Invalid authority.");
        }
    }

    @Override
    public void revokeOneAuthority(AuthorityDTO auth) {
        try {
            if (authMapper.deleteOneAuthorityToMember(auth) != 1)
                throw new RuntimeException("Failed to revoke user authority");
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new DuplicateKeyException("Duplicate authority");
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new DataIntegrityViolationException("Invalid authority.");
        }
    }

    @Override
    public void revokeAllAuthority(String userId) {
        authMapper.deleteAllAuthorityToMember((userId));
    }

    @Override
    public boolean hasRole(String userId, String role) {
        List<AuthorityDTO> authorities = authMapper.readAuthMember(userId);
        for (AuthorityDTO authority : authorities) {
            if (authority.getAuthority().equalsIgnoreCase(role) || authority.getAuthority().equalsIgnoreCase("ROLE_" + role))
                return true;
        }
        return false;
    }
}
