package com.perfect.community.api.service.user;

import com.google.common.base.Preconditions;
import com.perfect.community.api.dto.user.UserAuthoritiesDTO;
import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.vo.user.UserVO;
import com.perfect.community.api.mapper.user.UsersAuthoritiesMapper;
import com.perfect.community.api.mapper.user.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger();

    private final UsersMapper mapper;

    private final UsersAuthoritiesMapper usersAuthoritiesMapper;

    private final PasswordEncoder encoder;

    @Override
    public List<UserDTO> getUserList() {
        return mapper.selectAllUsers();
    }

    @Override
    public List<UserDTO> getUserListWithAuthorities() {
        return mapper.selectAllUsersWithAuthorities();
    }

    @Override
    public UserDTO getUserInfoByUserId(String userId) {
        return mapper.selectUserByUserId(userId);
    }

    @Override
    public UserDTO getUserInfoWithAuthoritiesByUserId(String userId) {
        return mapper.selectUserWithAuthoritiesByUserId(userId);
    }

    @Override
    public void createUser(UserDTO userDTO) throws RuntimeException {
        accountPolicyValidation(userDTO);
        if (!isValidUserId(userDTO.getUserId())) {
            throw new DuplicateKeyException("User ID is duplicated.");
        }
        if (!isValidNickname(userDTO.getNickname())) {
            throw new DuplicateKeyException("User nickname is duplicated.");
        }
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        if (mapper.insertUser(new UserVO(userDTO)) != 1) {
            throw new RuntimeException("Failed to create user.");
        }
        if (usersAuthoritiesMapper.insertUserAuthorities(UserAuthoritiesDTO.builder().userId(userDTO.getUserId()).authority(userDTO.getAuthority()).build()) != 1) {
            throw new RuntimeException("Failed to grant user authorities.");
        }
    }

    @Override
    public void updateUserInfo(UserDTO userDTO) {
        Preconditions.checkNotNull(userDTO.getUserId(), "User ID must be not null.");
        Preconditions.checkNotNull(userDTO.getNickname(), "User nickname must be not null.");
        if (mapper.updateUser(new UserVO(userDTO)) != 1)
            throw new RuntimeException("failed to update user info");
    }

    @Override
    public void changeUserPassword(UserDTO userDTO) {
        Preconditions.checkNotNull(userDTO.getUserId(), "User ID must be not null.");
        Preconditions.checkNotNull(userDTO.getPassword(), "User PW must be not null.");
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        if (mapper.updatePassword(new UserVO(userDTO)) != 1)
            throw new RuntimeException("failed to change user password");
    }

    @Override
    public void removeUser(String userId) {
        if (mapper.deleteUser(userId) != 1)
            throw new RuntimeException("That user doesn't exist.");
    }

    @Override
    public void disableUser(String userId) {
        if (mapper.disableUser(userId) != 1)
            throw new RuntimeException("User deactivation failed.");
    }

    @Override
    public void enableUser(String userId) {
        if (mapper.enableMember(userId) != 1)
            throw new RuntimeException("User activation failed.");
    }

    @Override
    public boolean verifyPassword(UserDTO userDTO) {
        Preconditions.checkNotNull(userDTO.getUserId(), "userId must be not null.");
        Preconditions.checkNotNull(userDTO.getPassword(), "password must be not null.");
        return encoder.matches(userDTO.getPassword(), mapper.selectUserByUserId(userDTO.getUserId()).getPassword());
    }

    @Override
    public boolean isValidUserId(String userId) {
        return mapper.userIdAvailability(userId);
    }

    @Override
    public boolean isValidNickname(String nickname) {
        return mapper.nicknameAvailability(nickname);
    }

    public void accountPolicyValidation(UserDTO user) {
        log.warn(user);
        Preconditions.checkNotNull(user.getUserId(), "User ID must be not null");
        Preconditions.checkState(!user.getUserId().isEmpty(), "User ID must be not empty.");
        Preconditions.checkState(user.getUserId().length() >= 5, "User ID must be at least 5 characters long.");

        Preconditions.checkNotNull(user.getPassword(), "User PW must be not null");
        Preconditions.checkState(!user.getPassword().isEmpty(), "User PW must be not empty.");
        Preconditions.checkState(user.getPassword().length() >= 4, "User PW must be at least 4 characters long.");

        Preconditions.checkNotNull(user.getNickname(), "User nickname must be not null");
        Preconditions.checkState(!user.getNickname().isEmpty(), "User nickname must be not empty.");
        Preconditions.checkState(user.getNickname().length() >= 2, "User nickname must be at least 2 characters long.");

        Preconditions.checkNotNull(user.getAuthority(), "User authority must be not null");
        Preconditions.checkState(!user.getAuthority().isEmpty(), "User nickname must be not empty.");
    }
}
