package com.board.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.board.api.dto.post.PostDTO;
import com.board.api.dto.post.PostSearchDTO;

import java.util.List;

@Repository
@Mapper
public interface PostSearchMapper {
    List<PostDTO> searchPostByKeyword(List<PostSearchDTO> searchConditions);
    List<PostDTO> searchPostByRegex(List<String> searchConditions);
}
