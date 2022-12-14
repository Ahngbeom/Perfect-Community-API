package com.board.api.mapper;

import com.board.api.dto.post.PostListOptDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.board.api.dto.post.PostDTO;

import java.util.List;

@Repository
@Mapper
public interface PostCRUD_Mapper {

    long countPosts(long boardNo);

    List<PostDTO> selectPostList(PostListOptDTO postListOptions);

    PostDTO selectPostInfoByPno(long pno);

    int insertPost(PostDTO board);

    int updatePost(PostDTO board);

    int deleteBoard(long bno);

    int deleteAllBoard();

}
