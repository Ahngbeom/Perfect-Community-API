package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostFilterDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getPostList(PostFilterDTO postListOptions);

    PostDTO getInfoByPno(long pno);

    long registration(String userId, PostDTO postDTO);

    long modification(long postNo, PostDTO postDTO);

    void remove(String userId, long pno);

    boolean isWriter(long pno, String userId);

    long getPostCount(PostFilterDTO postFilterDTO);

}
