package com.perfect.community.api.service.user;

import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger();

    private final UserMapper mapper;

    private final PasswordEncoder encoder;

    @Override
    public List<UserDTO> getUserList() {
        return (mapper.readAllMember());
    }

    @Override
    public void createUser(UserDTO user) throws RuntimeException {
        user.setPassword(encoder.encode(user.getPassword()));
        if (mapper.insertMember(user) != 1) {
            throw new RuntimeException("Create user failure");
        }
    }

    @Override
    public UserDTO getUserInfoById(String userId) {
        return mapper.readMemberByUserId(userId);
    }

    @Override
    public UserDTO getUserInfoByName(String userName) {
        return mapper.readMemberByUserName(userName);
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
        if (mapper.disableMember(userId) == 0)
            throw new RuntimeException("User deactivation failed.");
    }

    @Override
    public void enableUser(String userId) {
        if (mapper.enableMember(userId) == 0)
            throw new RuntimeException("User activation failed.");
    }

    @Override
    public boolean verifyPassword(String userId, String password) {
        return encoder.matches(password, mapper.getEncodePassword(userId));
    }

    @Override
    public UserDTO dtoConverter(UserDetails userDetails) {
        return null;
    }
}
