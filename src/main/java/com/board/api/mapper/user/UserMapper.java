package com.board.api.mapper.user;

import com.board.api.dto.auth.AuthorityDTO;
import com.board.api.dto.user.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    UserDTO readMemberByUserId(String userId);
    UserDTO readMemberByUserName(String userName);

    List<UserDTO> readAllMember();

    List<AuthorityDTO> readAuthMember(String userId);

    int updateUser(UserDTO user);

    int deleteUser(String userId);

    int insertMember(UserDTO member);

    int insertAuthorityToMember(AuthorityDTO auth);

    int deleteOneAuthorityToMember(AuthorityDTO auth);

    int deleteAllAuthorityToMember(String userId);

    int disableMember(String userId);

    int enableMember(String userId);

    String getEncodePassword(String userId);

}
