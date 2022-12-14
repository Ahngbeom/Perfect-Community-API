package com.board.api.service.board;

import com.board.api.dto.board.BoardDTO;
import com.board.api.mapper.board.BoardCRUD_Mapper;
import com.board.api.mapper.utils.UtilsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCRUD_ServiceImpl implements BoardCRUD_Service {

    private final BoardCRUD_Mapper mapper;

    private final UtilsMapper utilsMapper;

    @Override
    public List<BoardDTO> getBoardList() {
        return mapper.getBoardList();
    }

    @Override
    public BoardDTO getBoardInfo(long bno) throws RuntimeException {
        if (bno < 1)
            throw new RuntimeException("Invalid board number.");
        return mapper.getBoardInfo(bno);
    }

    @Override
    public void createBoard(BoardDTO boardDTO) throws RuntimeException {
        if (mapper.createBoard(boardDTO) != 1) {
            throw new RuntimeException("Failed to create board.");
        }
    }

    @Override
    public void updateBoard(BoardDTO boardDTO) throws RuntimeException {
        if (mapper.updateBoard(boardDTO) != 1) {
            throw new RuntimeException("Failed to update board.");
        }
    }

    @Override
    public void deleteBoard(long bno) {
        if (mapper.deleteBoard(bno) != 1) {
            throw new RuntimeException("Failed to delete board.");
        }
        utilsMapper.initializeAutoIncrement("boards");
    }
}
