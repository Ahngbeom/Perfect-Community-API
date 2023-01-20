package com.perfect.community.api.mapper.jwt;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface JwtMapper {

    int addInvalidAccessToken(String accessToken);
}
