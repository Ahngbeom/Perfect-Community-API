package com.perfect.community.api.mapper.auth;

import com.perfect.community.api.dto.auth.AuthorityDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AuthMapper {
    List<AuthorityDTO> readAuthMember(String userId);

    int insertAuthorityToMember(AuthorityDTO auth);

    int deleteOneAuthorityToMember(AuthorityDTO auth);

    int deleteAllAuthorityToMember(String userId);

}
