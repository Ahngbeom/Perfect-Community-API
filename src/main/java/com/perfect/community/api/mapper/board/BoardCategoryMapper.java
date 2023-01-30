package com.perfect.community.api.mapper.board;

import com.perfect.community.api.vo.board.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardCategoryMapper {

    int updateCategory(BoardVO boardVO);
}
