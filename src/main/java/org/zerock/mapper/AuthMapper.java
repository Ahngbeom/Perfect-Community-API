package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.zerock.DTO.AuthorityDTO;
import org.zerock.DTO.UserDTO;

import java.util.List;

@Repository
@Mapper
public interface AuthMapper {
    List<AuthorityDTO> readAuthMember(String userId);

    int insertAuthorityToMember(AuthorityDTO auth);

    int deleteOneAuthorityToMember(AuthorityDTO auth);

    int deleteAllAuthorityToMember(String userId);

}
