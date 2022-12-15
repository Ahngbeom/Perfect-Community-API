package com.board.api.service;

import com.board.api.dto.AuthorityDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.board.api.mapper.AuthMapper;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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
    public boolean hasAdminRole(String userId) {
        AtomicBoolean result = new AtomicBoolean(false);
//        getUserInfoById(userId).getAuthList().forEach(auth -> {
//            if (auth.equals("ROLE_ADMIN"))
//                result.set(true);
//        });
        return result.get();
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

}
