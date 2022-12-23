package com.perfect.community.api.mapper.utils;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UtilsMapper {
    long initializeAutoIncrement(String tableName);
}
