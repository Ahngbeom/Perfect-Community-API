package com.board.api.service.board;

import com.board.api.DTO.PostDTO;
import com.board.api.DTO.UserDTO;

import java.util.List;

public interface PostCRUD_Service {

    long countPosts();

    List<PostDTO> getBoardList();

    List<PostDTO> getBoardListWithPage(int page);

    PostDTO getBoardByBno(long bno);

    void registerBoard(PostDTO board);

    void modifyPost(PostDTO board);

    void removeBoard(long bno);

    int removeAllBoard();

    long initBnoValue();

    PostDTO authenticateForPosts(PostDTO board, UserDTO member);

}
