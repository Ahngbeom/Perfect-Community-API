package com.board.api.mapper.utils;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UtilsMapper {
    long initializeAutoIncrement(String tableName);
}
