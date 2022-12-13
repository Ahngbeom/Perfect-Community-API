package com.board.api.service.board;

import com.board.api.dto.PostDTO;
import com.board.api.dto.PostListOptDTO;

import java.util.List;

public interface PostCRUD_Service {

    long countPosts();

    List<PostDTO> getBoardList();

    List<PostDTO> getBoardListWithPage(PostListOptDTO postListOptions);

    PostDTO getPostByBno(long bno);

    void registerPost(PostDTO board);

    void modifyPost(PostDTO board);

    void removePost(long bno);

    int removeAllPost();

    long initPnoValue();

}
