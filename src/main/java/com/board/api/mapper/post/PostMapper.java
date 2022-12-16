package com.board.api.mapper.post;

import com.board.api.dto.post.PostListOptDTO;
import com.board.api.entity.post.PostEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.board.api.dto.post.PostDTO;

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

    int deleteAllPosts();

}