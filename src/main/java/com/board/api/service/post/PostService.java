package com.board.api.service.post;

import com.board.api.dto.post.PostDTO;
import com.board.api.dto.post.PostListOptDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getPostList(PostListOptDTO postListOptions);

    PostDTO getInfoByPno(long pno);

    PostDTO registration(String userId, PostDTO postDTO);

    void modification(String userId, PostDTO postDTO);

    void remove(String userId, long pno);

}
