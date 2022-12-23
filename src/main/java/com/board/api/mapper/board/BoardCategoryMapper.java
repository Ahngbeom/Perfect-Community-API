package com.board.api.mapper.board;

import com.board.api.entity.board.BoardEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardCategoryMapper {

    int updateCategory(BoardEntity boardEntity);
}
