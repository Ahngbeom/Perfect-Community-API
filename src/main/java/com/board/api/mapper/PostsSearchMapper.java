package com.board.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.board.api.DTO.PostsDTO;
import com.board.api.DTO.PostsSearchDTO;

import java.util.List;

@Repository
@Mapper
public interface PostsSearchMapper {
    List<PostsDTO> selectBoardByKeyword(PostsSearchDTO searchVO);
}
