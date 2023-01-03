package com.perfect.community.api.service.user;

import com.perfect.community.api.dto.user.UserDTO;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;

import java.util.List;

public interface UserService {
    List<UserDTO> getUserList();

    List<UserDTO> getUserListWithAuthorities();

    UserDTO getUserInfoByUserId(String userId);

    UserDTO getUserInfoWithAuthoritiesByUserId(String userId);

    void createUser(UserDTO member) throws DuplicateMemberException;

    void updateUserInfo(UserDTO user);

    void changeUserPassword(UserDTO user);

    void removeUser(String userId);

    void disableUser(String userId);

    void enableUser(String userId);

    boolean verifyPassword(UserDTO userDTO);

    boolean userIdAvailability(String userId);

    boolean nicknameAvailability(String nickname);

}
