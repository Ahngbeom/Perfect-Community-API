package com.board.api.mapper;

import com.board.api.DTO.AuthorityDTO;
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
