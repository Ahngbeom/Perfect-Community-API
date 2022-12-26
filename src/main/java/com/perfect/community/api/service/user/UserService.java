package com.perfect.community.api.service.user;

import com.perfect.community.api.dto.user.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    List<UserDTO> getUserList();

    void createUser(UserDTO member);

    UserDTO getUserInfoById(String userId);

    void updateUser(UserDTO user);

    void removeUser(String userId);

    void disableUser(String userId);

    void enableUser(String userId);

    boolean verifyPassword(String userId, String password);

    UserDTO dtoConverter(UserDetails userDetails);

}
