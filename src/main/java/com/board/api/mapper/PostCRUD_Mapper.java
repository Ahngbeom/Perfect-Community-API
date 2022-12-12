package com.board.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.board.api.DTO.PostDTO;
import com.board.api.DTO.UserDTO;

import java.util.List;

@Repository
@Mapper
public interface PostCRUD_Mapper {

    long countBoard();

    List<PostDTO> selectBoardList();

    List<PostDTO> selectBoardListWithPage(int page);

    PostDTO selectBoardByBno(long bno);

    int insertBoard(PostDTO board);

    int insertBoardWithPassword(@Param("bno") long bno, @Param("password") String password);

    int updatePost(PostDTO board);

    int updatePasswordForPost(@Param("bno") long bno, @Param("password") String password);

    int deleteBoard(long bno);

    int deleteAllBoard();

    int deletePasswordForPost(long bno);

    long initAutoIncrement();

    PostDTO authenticateForPosts(@Param("board") PostDTO board, @Param("member") UserDTO member);

    String getPostPassword(long bno);
}
