package com.perfect.community.api.mapper.board;

import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.vo.board.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {
    List<BoardVO> getBoardList();
    BoardVO getBoardInfo(long bno);
    int createBoard(BoardDTO boardDTO);
    int updateBoard(BoardDTO boardDTO);
    int deleteBoard(long bno);
    int deleteAll();

    boolean isHeTheOwnerOfBoard(@Param("userId") String userId, @Param("bno") long bno);
}
