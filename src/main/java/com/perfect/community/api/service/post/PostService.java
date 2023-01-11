package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostFilterDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getPostList(PostFilterDTO postListOptions);

    PostDTO getInfoByPno(long pno);

    void registration(String userId, PostDTO postDTO);

    void modification(long postNo, String userId, PostDTO postDTO);

    void remove(String userId, long pno);

    boolean isWriter(long pno, String userId);

    long getPostCount(PostFilterDTO postFilterDTO);

}
