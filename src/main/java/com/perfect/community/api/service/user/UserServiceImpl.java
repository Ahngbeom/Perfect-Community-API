package com.perfect.community.api.service.user;

import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.mapper.user.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger();

    private final UsersMapper mapper;

    private final PasswordEncoder encoder;

    @Override
    public List<UserDTO> getUserList() {
        return mapper.selectAllUsers().stream().map(entity -> entity.toDTO()).collect(Collectors.toList());
    }

    @Override
    public void createUser(UserDTO user) throws RuntimeException {
        user.setPassword(encoder.encode(user.getPassword()));
        if (mapper.insertUser(user) != 1) {
            throw new RuntimeException("Create user failure");
        }
    }

    @Override
    public UserDTO getUserInfoById(String userId) {
        return mapper.selectUserByUserId(userId).toDTO();
    }

    @Override
    public void updateUser(UserDTO user) {
        user.setPassword(encoder.encode(user.getPassword()));
        if (mapper.updateUser(user) == 0)
            throw new RuntimeException("User information update failed.");
    }

    @Override
    public void removeUser(String userId) {
        if (mapper.deleteUser(userId) == 0)
            throw new RuntimeException("That user doesn't exist.");
    }

    @Override
    public void disableUser(String userId) {
        if (mapper.disableUser(userId) == 0)
            throw new RuntimeException("User deactivation failed.");
    }

    @Override
    public void enableUser(String userId) {
        if (mapper.enableMember(userId) == 0)
            throw new RuntimeException("User activation failed.");
    }

    @Override
    public boolean verifyPassword(String userId, String password) {
        return encoder.matches(password,  mapper.selectUserByUserId(userId).getPassword());
    }

    @Override
    public UserDTO dtoConverter(UserDetails userDetails) {
        return null;
    }
}
