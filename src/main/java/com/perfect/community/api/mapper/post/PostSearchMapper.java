package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.perfect.community.api.dto.post.PostExtractionDTO;

import java.util.List;

@Repository
@Mapper
public interface PostSearchMapper {
    List<PostDTO> searchPostByKeyword(List<PostExtractionDTO> searchConditions);
    List<PostDTO> searchPostByRegex(List<String> searchConditions);
}
