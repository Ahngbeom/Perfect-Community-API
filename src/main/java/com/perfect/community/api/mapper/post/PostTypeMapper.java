package com.perfect.community.api.mapper.post;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PostTypeMapper{
    List<String> selectAllPostType();
}
