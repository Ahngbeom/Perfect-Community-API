package com.board.api.service.board;

import com.board.api.dto.board.BoardDTO;
import com.board.api.entity.board.BoardEntity;
import com.board.api.mapper.board.BoardMapper;
import com.board.api.mapper.utils.UtilsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper mapper;

    private final UtilsMapper utilsMapper;

    @Override
    public List<BoardDTO> getBoardList() {
        return mapper.getBoardList().stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public BoardDTO getBoardInfo(long bno) throws RuntimeException {
        if (bno < 1)
            throw new RuntimeException("Invalid board number.");
        return entityToDTO(mapper.getBoardInfo(bno));
    }

    @Override
    public BoardDTO createBoard(BoardDTO boardDTO) throws RuntimeException {
//        if (boardDTO.getTitle().contains(" "))
//            throw new RuntimeException("Notice board names do not allow spaces.");
        BoardEntity entity = BoardEntity.dtoToEntity(boardDTO);
        if (mapper.createBoard(entity) != 1) {
            throw new RuntimeException("Failed to create board.");
        }
        return entityToDTO(entity);
    }

    @Override
    public void updateBoard(long boardNo, String userId, BoardDTO boardDTO) throws RuntimeException {
        if (!matchUserIdAndBoardCreator(userId, boardNo))
            throw new RuntimeException("You do not have permission to edit this board.");
        boardDTO.setBno(boardNo);
        BoardEntity boardEntity = BoardEntity.dtoToEntity(boardDTO);
        if (mapper.updateBoard(boardEntity) != 1) {
            throw new RuntimeException("Failed to update board.");
        }
    }

    @Override
    public void deleteBoard(String userId, long bno) {
        if (!matchUserIdAndBoardCreator(userId, bno))
            throw new RuntimeException("You do not have permission to delete this board.");
        if (mapper.deleteBoard(bno) != 1)
            throw new RuntimeException("Failed to delete board.");
        utilsMapper.initializeAutoIncrement("boards");
    }

    public boolean matchUserIdAndBoardCreator(String userId, long boardNo) {
        return userId.equals(getBoardInfo(boardNo).getCreatedUser());
    }

    public BoardDTO entityToDTO(BoardEntity entity) {
        return BoardDTO.builder()
                .bno(entity.getBno())
                .createdUser(entity.getCreateUser())
                .title(entity.getTitle())
                .comment(entity.getComment())
                .createDate(entity.getCreateDate())
                .updateDate(entity.getUpdateDate())
                .build();
    }
}