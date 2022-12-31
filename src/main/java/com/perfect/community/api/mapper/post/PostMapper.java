package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostExtractionDTO;
import com.perfect.community.api.entity.post.PostEntity;
import com.perfect.community.api.dto.post.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PostMapper {

    long countPosts();
    long countPosts(PostExtractionDTO.List options);

    List<PostEntity> selectPostList(PostExtractionDTO.List listOpt);

    PostEntity selectPostInfoByPno(long pno);

    int insertPost(PostEntity post);

    int updatePost(PostEntity post);

    int deletePost(long pno);

    int deletePostByBoardNo(long boardNo);

    int deleteAllPosts();

}
