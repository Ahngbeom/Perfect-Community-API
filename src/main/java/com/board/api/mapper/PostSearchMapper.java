package com.board.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.board.api.DTO.PostDTO;
import com.board.api.DTO.PostSearchDTO;

import java.util.List;

@Repository
@Mapper
public interface PostSearchMapper {
    List<PostDTO> searchPostWithMultipleConditions(List<PostSearchDTO> searchConditions);
}
