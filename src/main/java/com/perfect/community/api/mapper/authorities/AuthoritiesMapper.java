package com.perfect.community.api.mapper.authorities;

import com.perfect.community.api.dto.authorities.AuthoritiesDTO;
import com.perfect.community.api.vo.authorities.AuthoritiesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AuthoritiesMapper {
    List<AuthoritiesEntity> selectAllAuthority();

    int insertAuthority(AuthoritiesDTO authorities);

    int deleteAuthority(AuthoritiesDTO authorities);

    int updateAuthority(@Param("origAuthority") String origAuthority, @Param("renameAuthority") String renameAuthority);

}
