package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostFilterDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PostSearchMapper {
    List<PostDTO> searchPostByKeyword(List<PostFilterDTO> searchConditions);
    List<PostDTO> searchPostByRegex(List<String> searchConditions);
}
