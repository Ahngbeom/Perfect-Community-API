package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostListOptDTO;
import com.perfect.community.api.entity.post.PostEntity;
import com.perfect.community.api.dto.post.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PostMapper {

    long countPosts(long boardNo);

    List<PostEntity> selectPostList(PostListOptDTO postListOptions);

    PostEntity selectPostInfoByPno(long pno);

    int insertPost(PostEntity post);

    int updatePost(PostDTO post);

    int deletePost(long pno);

    int deletePostByBoardNo(long boardNo);

    int deleteAllPosts();

}
