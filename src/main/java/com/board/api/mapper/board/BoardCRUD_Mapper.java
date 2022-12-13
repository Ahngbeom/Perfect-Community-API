package com.board.api.mapper.board;

import com.board.api.dto.board.BoardDTO;

import java.util.List;

public interface BoardCRUD_Mapper {

    List<BoardDTO> getBoardList();
    int createBoard(BoardDTO boardDTO);
}
