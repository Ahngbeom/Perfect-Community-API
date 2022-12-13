package com.board.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import com.board.api.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUserList();

    void createUser(UserDTO member);

    UserDTO getUserInfoById(String userId);

    UserDTO getUserInfoByName(String userName);

    void updateUser(UserDTO user);

    void removeUser(String userId);

    void disableUser(String userId);

    void enableUser(String userId);

    boolean verifyPassword(String userId, String password);

    UserDTO dtoConverter(UserDetails userDetails);

}
