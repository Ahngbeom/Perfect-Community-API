package com.perfect.community.api.mapper.user;

import com.perfect.community.api.dto.user.UserDTO;
import com.perfect.community.api.entity.user.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UsersMapper {
    UserEntity selectUserByUserId(String userId);

    UserEntity selectUserWithAuthoritiesByUserId(String userId);

    List<UserEntity> selectAllUsers();

    List<UserEntity> selectAllUsersWithAuthorities();

    int insertUser(UserDTO user);

    int updateUser(UserDTO user);

    int updatePassword(UserDTO user);

    int deleteUser(String userId);

    int enableMember(String userId);

    int disableUser(String userId);

    boolean userIdAvailability(String userId);

    boolean nicknameAvailability(String nickname);
}
