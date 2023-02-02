package com.perfect.community.api.service.board;

import com.google.common.base.Preconditions;
import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.vo.board.BoardVO;
import com.perfect.community.api.mapper.board.BoardMapper;
import com.perfect.community.api.mapper.post.PostMapper;
import lombok.RequiredArgsConstructor;
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
        return mapper.getBoardList().stream().map(BoardDTO::new).collect(Collectors.toList());
    }

    @Override
    public BoardDTO getBoardInfo(long bno) {
        BoardVO boardVO = mapper.getBoardInfo(bno);
        return boardVO != null ? new BoardDTO(boardVO) : null;
    }

    @Override
    public long createBoard(String createUser, BoardDTO boardDTO) {
        Preconditions.checkNotNull(boardDTO.getTitle(), "[title] must not be null");
        Preconditions.checkState(!boardDTO.getTitle().isEmpty(), "[title] must not be empty");
        boardDTO.setCreateUser(createUser);
        if (mapper.createBoard(boardDTO) != 1) {
            throw new RuntimeException("Failed to create board.");
        }
        return boardDTO.getBno();
    }

    @Override
    public void updateBoard(String userId, long boardNo, BoardDTO boardDTO) {
        Preconditions.checkNotNull(boardDTO.getTitle(), "[title] must not be null");
//        if (notMatchUserIdAndBoardCreator(userId, boardNo))
//            throw new AccessDeniedException("You do not have permission to edit this board.");
        boardDTO.setBno(boardNo);
        if (mapper.updateBoard(boardDTO) != 1) {
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

}
