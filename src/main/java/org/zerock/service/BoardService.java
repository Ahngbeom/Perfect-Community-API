package org.zerock.service;

import org.zerock.DTO.PostsSearchDTO;
import org.zerock.DTO.PostsDTO;
import org.zerock.DTO.UserDTO;

import java.util.List;

public interface BoardService {

    long countPosts();

    List<PostsDTO> getBoardList();

    List<PostsDTO> getBoardListWithPage(int page);

    PostsDTO getBoardByBno(long bno);

    List<PostsDTO> searchBoardByKeyword(PostsSearchDTO searchVO);

    int registerBoard(PostsDTO board);

    int modifyPost(PostsDTO board);

    int modifyPostWithPassword(PostsDTO board, boolean isAdmin);

    int removeBoard(long bno);

    int removePostWithPassword(PostsDTO board, boolean isAdmin);

    int removeAllBoard();

    long initBnoValue();

    PostsDTO authenticateForPosts(PostsDTO board, UserDTO member);

    boolean postHasPassword(long bno);

    boolean postPasswordMatches(long bno, String password);
}
