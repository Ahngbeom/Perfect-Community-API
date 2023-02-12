package com.perfect.community.api.service.user;

import com.google.common.base.Preconditions;
import com.perfect.community.api.dto.user.UserAuthoritiesDTO;
import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.mapper.user.UsersAuthoritiesMapper;
import com.perfect.community.api.mapper.user.UsersMapper;
import com.perfect.community.api.service.redis.RedisService;
import com.perfect.community.api.vo.user.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UsersMapper mapper;

    private final UsersAuthoritiesMapper usersAuthoritiesMapper;

    private final BCryptPasswordEncoder encoder;

    private final RedisService redisService;

    public List<UserDTO> getUserList() {
        return mapper.selectAllUsers();
    }

    public List<UserDTO> getUserListWithAuthorities() {
        List<UserDTO> userDTOList = mapper.selectAllUsers();
        return userDTOList.stream().peek(userDTO -> userDTO.setAuthorities(usersAuthoritiesMapper.selectAllUserAuthoritiesByUserId(userDTO.getUserId()))).collect(Collectors.toList());
    }

    public UserDTO getUserInfoByUserId(String userId) {
        return mapper.selectUserByUserId(userId);
    }

    public UserDTO getUserInfoWithAuthoritiesByUserId(String userId) {
        UserDTO userDTO = mapper.selectUserByUserId(userId);
        userDTO.setAuthorities(usersAuthoritiesMapper.selectAllUserAuthoritiesByUserId(userId));
        return userDTO;
    }

    @Transactional
    public String createUser(UserDTO userDTO) throws RuntimeException {
        accountPolicyValidation(userDTO);
        if (!isValidUserId(userDTO.getUserId())) {
            throw new DuplicateKeyException("User ID is duplicated.");
        }
        if (!isValidNickname(userDTO.getNickname())) {
            throw new DuplicateKeyException("User nickname is duplicated.");
        }
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        UserVO userVO = new UserVO(userDTO);
        if (mapper.insertUser(userVO) != 1) {
            throw new RuntimeException("Failed to create user.");
        }
        if (usersAuthoritiesMapper.insertUserAuthorities(
                UserAuthoritiesDTO.builder()
                        .userId(userDTO.getUserId())
                        .authorities(userDTO.getAuthorities())
                        .build()) != 1) {
            throw new RuntimeException("Failed to grant user authorities.");
        }
        return userVO.getUser_id();
    }

    @Transactional
    public void updateUserInfo(UserDTO userDTO) {
        Preconditions.checkNotNull(userDTO.getUserId(), "User ID must be not null.");
        Preconditions.checkNotNull(userDTO.getNickname(), "User nickname must be not null.");
        if (mapper.updateUser(new UserVO(userDTO)) != 1)
            throw new RuntimeException("failed to update user info");
    }

    @Transactional
    public void secessionUser(String userId) {
        if (mapper.deleteUser(userId) != 1)
            throw new RuntimeException("That user doesn't exist.");
    }

    @Transactional
    public void changeUserPassword(UserDTO userDTO) {
        Preconditions.checkNotNull(userDTO.getUserId(), "User ID must be not null.");
        Preconditions.checkNotNull(userDTO.getPassword(), "User PW must be not null.");
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        if (mapper.updatePassword(new UserVO(userDTO)) != 1)
            throw new RuntimeException("failed to change user password");
    }

    @Transactional
    public void disableUser(String userId) {
        if (mapper.disableUser(userId) != 1)
            throw new RuntimeException("User deactivation failed.");
    }

    @Transactional
    public void enableUser(String userId) {
        if (mapper.enableUser(userId) != 1)
            throw new RuntimeException("User activation failed.");
    }

    @Transactional(readOnly = true)
    public void isAdmin(String userId) {
        usersAuthoritiesMapper.hasAuthority(userId, "ROLE_ADMIN");
    }

    public boolean verifyPassword(String userId, String password) {
        Preconditions.checkNotNull(userId, "userId must be not null.");
        Preconditions.checkNotNull(password, "password must be not null.");
        return encoder.matches(password, mapper.selectUserByUserId(userId).getPassword());
    }

    public boolean isValidUserId(String userId) {
        userIdPolicyValidation(userId);
        return mapper.userIdAvailability(userId);
    }

    public boolean isValidPassword(String password) {
        passwordPolicyValidation(password);
        return true;
    }

    public boolean isValidNickname(String nickname) {
        nicknamePolicyValidation(nickname);
        return mapper.nicknameAvailability(nickname);
    }

    public void userIdPolicyValidation(String userId) {
        Preconditions.checkNotNull(userId, "User ID must be not null");
        Preconditions.checkState(!userId.isEmpty(), "User ID must be not empty.");
        Preconditions.checkState(userId.length() >= 5, "User ID must be at least 5 characters long.");
    }

    public void passwordPolicyValidation(String password) {
        Preconditions.checkNotNull(password, "User PW must be not null");
        Preconditions.checkState(!password.isEmpty(), "User PW must be not empty.");
        Preconditions.checkState(password.length() >= 4, "User PW must be at least 4 characters long.");
    }

    public void nicknamePolicyValidation(String nickname) {
        Preconditions.checkNotNull(nickname, "User nickname must be not null");
        Preconditions.checkState(!nickname.isEmpty(), "User nickname must be not empty.");
        Preconditions.checkState(nickname.length() >= 2, "User nickname must be at least 2 characters long.");
    }

    public void accountPolicyValidation(UserDTO user) {
        userIdPolicyValidation(user.getUserId());
        passwordPolicyValidation(user.getPassword());
        nicknamePolicyValidation(user.getNickname());

        Preconditions.checkNotNull(user.getAuthorities(), "User authority must be not null");
        Preconditions.checkState(!user.getAuthorities().isEmpty(), "User nickname must be not empty.");
    }
}
