package com.perfect.community.api.service.user;

import com.perfect.community.api.dto.user.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUserList();

    List<UserDTO> getUserListWithAuthorities();

    UserDTO getUserInfoByUserId(String userId);

    UserDTO getUserInfoWithAuthoritiesByUserId(String userId);

    void createUser(UserDTO member);

    void updateUserInfo(UserDTO user);

    void changeUserPassword(UserDTO user);

    void removeUser(String userId);

    void disableUser(String userId);

    void enableUser(String userId);

    boolean verifyPassword(String userId, String password);

}
