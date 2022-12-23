package com.perfect.community.api.mapper.board;

import com.perfect.community.api.entity.board.BoardEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {
    List<BoardEntity> getBoardList();
    BoardEntity getBoardInfo(long bno);
    int createBoard(BoardEntity boardEntity);
    int updateBoard(BoardEntity boardEntity);
    int deleteBoard(long bno);
    int deleteAll();
}
