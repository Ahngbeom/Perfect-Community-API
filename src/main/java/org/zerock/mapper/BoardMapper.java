package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zerock.domain.BoardSearchVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.MemberVO;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

    long countBoard();

    List<BoardVO> selectBoardList();

    List<BoardVO> selectBoardListWithPage(int page);

    BoardVO selectBoardByBno(long bno);

    List<BoardVO> selectBoardByKeyword(BoardSearchVO searchVO);

    int insertBoard(BoardVO board);

    int insertBoardWithPassword(@Param("bno") long bno, @Param("password") String password);

    int updateBoard(BoardVO board);

    int deleteBoard(long bno);

    int deleteAllBoard();

    int deletePasswordForPost(long bno);

    long initAutoIncrement();

    BoardVO authenticateForPosts(@Param("board") BoardVO board, @Param("member") MemberVO member);

    String postHasPassword(long bno);
}
