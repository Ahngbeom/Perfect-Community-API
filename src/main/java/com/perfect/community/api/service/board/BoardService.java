package com.perfect.community.api.service.board;

import com.perfect.community.api.dto.board.BoardDTO;

import javax.security.sasl.AuthenticationException;
import java.util.List;

public interface BoardService {

    List<BoardDTO> getBoardList();

    BoardDTO getBoardInfo(long bno);

    BoardDTO createBoard(String createUser, BoardDTO boardDTO);

    void updateBoard(String userId, long boardNo, BoardDTO boardDTO) throws AuthenticationException;

    void deleteBoard(String userId, long bno);

}
