package com.board.api.service.post;

import com.board.api.dto.post.PostDTO;
import com.board.api.dto.post.PostListOptDTO;

import java.util.List;

public interface PostCRUD_Service {

    long countPosts(long boardNo);

    List<PostDTO> getPostList(PostListOptDTO postListOptions);

    PostDTO getInfoByBno(long bno);

    void registration(PostDTO board);

    void modification(PostDTO board);

    void remove(long bno);

    int removeAll();

    long initPnoValue();

}
