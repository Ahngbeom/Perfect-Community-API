package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zerock.domain.BoardVO;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

    long countBoard();

    List<BoardVO> selectBoardList();

    List<BoardVO> selectBoardListWithPage(int page);

    BoardVO selectBoardByBno(long bno);

    List<BoardVO> selectBoardByKeyword(String keyword);

    int insertBoard(BoardVO board);

    int updateBoard(BoardVO board);

    int deleteBoard(long bno);

    int deleteAllBoard();

    long initAutoIncrement();
}
