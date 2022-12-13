package com.board.api.service.board;

import com.board.api.dto.board.BoardDTO;

import java.util.List;

public interface BoardCRUD_Service {

    List<BoardDTO> getBoardList();

    BoardDTO getBoardInfo(long bno);

    void createBoard(BoardDTO boardDTO);
}
