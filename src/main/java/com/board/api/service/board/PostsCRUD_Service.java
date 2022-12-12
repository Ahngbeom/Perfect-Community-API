package com.board.api.service.board;

import com.board.api.DTO.PostsDTO;
import com.board.api.DTO.UserDTO;

import java.util.List;

public interface PostsCRUD_Service {

    long countPosts();

    List<PostsDTO> getBoardList();

    List<PostsDTO> getBoardListWithPage(int page);

    PostsDTO getBoardByBno(long bno);

    void registerBoard(PostsDTO board);

    void modifyPost(PostsDTO board);

    int modifyPostWithPassword(PostsDTO board, boolean isAdmin);

    void removeBoard(long bno);

    int removePostWithPassword(PostsDTO board, boolean isAdmin);

    int removeAllBoard();

    long initBnoValue();

    PostsDTO authenticateForPosts(PostsDTO board, UserDTO member);

    boolean postHasPassword(long bno);

    boolean postPasswordMatches(long bno, String password);
}
