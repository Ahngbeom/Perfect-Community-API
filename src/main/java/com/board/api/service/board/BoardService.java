package com.board.api.service.board;

import com.board.api.dto.board.BoardDTO;

import java.util.List;

public interface BoardService {

    List<BoardDTO> getBoardList();

    BoardDTO getBoardInfo(long bno);

    BoardDTO createBoard(BoardDTO boardDTO);

    void updateBoard(long boardNo, String userId, BoardDTO boardDTO);

    void deleteBoard(String userId, long bno);
}
