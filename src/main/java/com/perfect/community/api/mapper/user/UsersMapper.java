package com.perfect.community.api.mapper.user;

import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.vo.user.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UsersMapper {
    UserDTO selectUserByUserId(String userId);

    UserDTO selectUserWithAuthoritiesByUserId(String userId);

    List<UserDTO> selectAllUsers();

    List<UserDTO> selectAllUsersWithAuthorities();

    int insertUser(UserVO user);

    int updateUser(UserVO user);

    int updatePassword(UserVO user);

    int deleteUser(String userId);

    int enableMember(String userId);

    int disableUser(String userId);

    boolean userIdAvailability(String userId);

    boolean nicknameAvailability(String nickname);
}
