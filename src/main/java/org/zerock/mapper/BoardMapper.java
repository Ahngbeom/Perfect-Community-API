package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.zerock.DTO.PostsSearchDTO;
import org.zerock.DTO.PostsDTO;
import org.zerock.DTO.UserDTO;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

    long countBoard();

    List<PostsDTO> selectBoardList();

    List<PostsDTO> selectBoardListWithPage(int page);

    PostsDTO selectBoardByBno(long bno);

    List<PostsDTO> selectBoardByKeyword(PostsSearchDTO searchVO);

    int insertBoard(PostsDTO board);

    int insertBoardWithPassword(@Param("bno") long bno, @Param("password") String password);

    int updatePost(PostsDTO board);

    int updatePasswordForPost(@Param("bno") long bno, @Param("password") String password);

    int deleteBoard(long bno);

    int deleteAllBoard();

    int deletePasswordForPost(long bno);

    long initAutoIncrement();

    PostsDTO authenticateForPosts(@Param("board") PostsDTO board, @Param("member") UserDTO member);

    String getPostPassword(long bno);
}
