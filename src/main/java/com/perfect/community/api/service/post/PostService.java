package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostExtractionDTO;
import com.perfect.community.api.dto.post.PostListOptDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getPostList(PostExtractionDTO.List postListOptions);

    PostDTO getInfoByPno(long pno);

    PostDTO registration(String userId, PostDTO postDTO);

    void modification(long postNo, String userId, PostDTO postDTO);

    void remove(String userId, long pno);

}
