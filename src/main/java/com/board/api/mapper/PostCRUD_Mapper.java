package com.board.api.mapper;

import com.board.api.dto.PostListOptDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.board.api.dto.PostDTO;

import java.util.List;

@Repository
@Mapper
public interface PostCRUD_Mapper {

    long countBoard();

    List<PostDTO> selectBoardList();

    List<PostDTO> selectBoardListWithPage(PostListOptDTO postListOptions);

    PostDTO selectBoardByPno(long pno);

    int insertBoard(PostDTO board);

    int updatePost(PostDTO board);

    int deleteBoard(long bno);

    int deleteAllBoard();

    long initAutoIncrement();

}
