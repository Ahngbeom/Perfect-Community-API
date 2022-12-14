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
//        if (boardDTO.getTitle().contains(" "))
//            throw new RuntimeException("Notice board names do not allow spaces.");
        if (mapper.createBoard(boardDTO) != 1) {
            throw new RuntimeException("Failed to create board.");
        }
    }

    @Override
    public void updateBoard(String userId, BoardDTO boardDTO) throws RuntimeException {
        if (!boardAuthorizationVerify(userId, boardDTO.getBno()))
            throw new RuntimeException("You do not have permission to edit this board.");
        if (mapper.updateBoard(boardDTO) != 1) {
            throw new RuntimeException("Failed to update board.");
        }
    }

    @Override
    public void deleteBoard(String userId, long bno) {
        if (!boardAuthorizationVerify(userId, bno))
            throw new RuntimeException("You do not have permission to delete this board.");
        if (mapper.deleteBoard(bno) != 1)
            throw new RuntimeException("Failed to delete board.");
        utilsMapper.initializeAutoIncrement("boards");
    }

    public boolean boardAuthorizationVerify(String userId, long boardNo) {
        return userId.equals(getBoardInfo(boardNo).getCreateUser());
    }
}
