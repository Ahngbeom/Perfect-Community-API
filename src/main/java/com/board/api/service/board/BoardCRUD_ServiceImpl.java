package com.board.api.service.board;

import com.board.api.dto.board.BoardDTO;
import com.board.api.mapper.board.BoardCRUD_Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCRUD_ServiceImpl implements BoardCRUD_Service {

    private final BoardCRUD_Mapper mapper;

    @Override
    public List<BoardDTO> getBoardList() {
        return mapper.getBoardList();
    }

    @Override
    public void createBoard(BoardDTO boardDTO) throws RuntimeException {
        if (mapper.createBoard(boardDTO) != 1) {
            throw new RuntimeException("Failed to create board.");
        }
    }
}
