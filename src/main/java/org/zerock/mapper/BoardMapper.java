package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.zerock.domain.BoardVO;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

    List<BoardVO> selectBoardList();

    BoardVO selectBoard(long bno);

    BoardVO selectBoardByKeyword(String keyword);

    int insertBoard(BoardVO board);

    int updateBoard(BoardVO board);

    int deleteBoard(long bno);
}
