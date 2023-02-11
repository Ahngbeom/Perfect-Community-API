package com.perfect.community.api.mapper.user;

import com.perfect.community.api.dto.user.UserAuthoritiesDTO;
import com.perfect.community.api.vo.user.UserAuthorityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@Mapper
public interface UsersAuthoritiesMapper {
    List<UserAuthorityEntity> selectAllUserAuthorities();

    Set<String> selectAllUserAuthoritiesByUserId(String userId);

    int insertUserAuthorities(UserAuthoritiesDTO userAuthorities);

    int deleteUserAuthorities(UserAuthoritiesDTO userAuthorities);

    int deleteAllUserAuthorities(String userId);

    boolean hasAuthority(@Param("userId") String userId, @Param("authority") String authority);

}
