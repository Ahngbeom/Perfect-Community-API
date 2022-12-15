package com.board.api.service.post;

import com.board.api.dto.post.PostDTO;
import com.board.api.dto.post.PostListOptDTO;

import java.util.List;

public interface PostCRUD_Service {

    long countPosts(long boardNo);

    List<PostDTO> getPostList(PostListOptDTO postListOptions);

    PostDTO getInfoByPno(long pno);

    void registration(String userId, PostDTO postDTO);

    void modification(PostDTO postDTO, String userName);

    void remove(String userName, long pno);

    int removeAll();

    long initPnoValue();

    boolean verifyPostType(String type);

}
