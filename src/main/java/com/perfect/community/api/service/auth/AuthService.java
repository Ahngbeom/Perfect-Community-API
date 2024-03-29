package com.perfect.community.api.service.auth;

import com.perfect.community.api.dto.authorities.AuthoritiesDTO;
import com.perfect.community.api.mapper.authorities.AuthoritiesMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger log = LogManager.getLogger();

    private final AuthoritiesMapper authoritiesMapper;

    public List<AuthoritiesDTO> getAuthorities() {
        return authoritiesMapper.selectAllAuthority().stream().map(entity -> entity.toDTO()).collect(Collectors.toList());
    }

    public void addAuthority(AuthoritiesDTO auth) {
        try {
            if (authoritiesMapper.insertAuthority(auth) != 1)
                throw new RuntimeException("Failed to add authority");
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new DuplicateKeyException("Duplicate authority");
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new DataIntegrityViolationException("Invalid authority.");
        }
    }

    public void removeAuthority(AuthoritiesDTO auth) {
        try {
            if (authoritiesMapper.deleteAuthority(auth) != 1)
                throw new RuntimeException("Failed to remove authority");
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new DuplicateKeyException("Duplicate authority");
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new DataIntegrityViolationException("Invalid authority.");
        }
    }

    public void modifyAuthority(String origAuthority, String renameAuthority) {
        if (authoritiesMapper.updateAuthority(origAuthority, renameAuthority) != 1)
            throw new RuntimeException("Failed to update authority");
    }
}
