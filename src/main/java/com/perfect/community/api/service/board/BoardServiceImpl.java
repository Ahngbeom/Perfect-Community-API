package com.perfect.community.api.service.board;

import com.google.common.base.Preconditions;
import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.entity.board.BoardEntity;
import com.perfect.community.api.mapper.board.BoardMapper;
import com.perfect.community.api.mapper.post.PostMapper;
import com.perfect.community.api.mapper.user.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper mapper;

    private final PostMapper postMapper;

    @Override
    public List<BoardDTO> getBoardList() {
        return mapper.getBoardList().stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public BoardDTO getBoardInfo(long bno) {
        return entityToDTO(Preconditions.checkNotNull(mapper.getBoardInfo(bno), "Invalid board number."));
    }

    @Override
    public long createBoard(String createUser, BoardDTO boardDTO) {
        Preconditions.checkNotNull(boardDTO.getTitle(), "[title] must not be null");
        boardDTO.setCreateUser(Preconditions.checkNotNull(createUser, "[createUser] must not be null"));
        BoardEntity entity = BoardEntity.dtoToEntity(boardDTO);
        if (mapper.createBoard(entity) != 1) {
            throw new RuntimeException("Failed to create board.");
        }
        return entity.getBno();
    }

    @Override
    public void updateBoard(String userId, long boardNo, BoardDTO boardDTO) {
        Preconditions.checkNotNull(boardDTO.getTitle(), "[title] must not be null");
//        if (notMatchUserIdAndBoardCreator(userId, boardNo))
//            throw new AccessDeniedException("You do not have permission to edit this board.");
        boardDTO.setBno(boardNo);
        BoardEntity boardEntity = BoardEntity.dtoToEntity(boardDTO);
        if (mapper.updateBoard(boardEntity) != 1) {
            throw new RuntimeException("Failed to update board. Make sure it's a valid board number");
        }
    }

    @Override
    public void deleteBoard(String userId, long bno) {
//        if (notMatchUserIdAndBoardCreator(userId, bno))
//            throw new AccessDeniedException("You do not have permission to delete this board.");
        postMapper.deletePostByBoardNo(bno);
        if (mapper.deleteBoard(bno) != 1)
            throw new RuntimeException("Failed to delete board. Make sure it's a valid board number");
    }

    @Override
    public boolean isHeTheOwnerOfBoard(String userId, long bno) {
        return mapper.isHeTheOwnerOfBoard(userId, bno);
    }

//    public boolean notMatchUserIdAndBoardCreator(String userId, long boardNo) {
//        return !userId.equals(getBoardInfo(boardNo).getCreateUser());
//    }

    public BoardDTO entityToDTO(BoardEntity entity) {
        return BoardDTO.builder()
                .bno(entity.getBno())
                .createUser(entity.getCreateUser())
                .title(entity.getTitle())
                .comment(entity.getComment())
                .createDate(entity.getCreateDate())
                .updateDate(entity.getUpdateDate())
                .build();
    }
}
