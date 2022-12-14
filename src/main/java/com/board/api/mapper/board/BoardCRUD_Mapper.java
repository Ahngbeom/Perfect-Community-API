package com.board.api.mapper.board;

import com.board.api.dto.board.BoardDTO;

import java.util.List;

public interface BoardCRUD_Mapper {

    List<BoardDTO> getBoardList();
    BoardDTO getBoardInfo(long bno);
    int createBoard(BoardDTO boardDTO);
    int updateBoard(BoardDTO boardDTO);
    int deleteBoard(long bno);
}
