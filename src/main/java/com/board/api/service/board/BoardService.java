package com.board.api.service.board;

import com.board.api.dto.board.BoardDTO;

import java.util.List;

public interface BoardService {

    List<BoardDTO> getBoardList();

    BoardDTO getBoardInfo(long bno);

    BoardDTO createBoard(String createUser, BoardDTO boardDTO);

    void updateBoard(String userId, long boardNo, BoardDTO boardDTO);

    void deleteBoard(String userId, long bno);

}
