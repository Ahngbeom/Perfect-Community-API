package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.zerock.DTO.AuthorityDTO;
import org.zerock.DTO.UserDTO;

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
