package com.perfect.community.api.mapper.post;

import com.perfect.community.api.dto.post.PostFilterDTO;
import com.perfect.community.api.vo.post.PostVO;
import com.perfect.community.api.dto.post.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PostMapper {

    long countPosts(PostFilterDTO options);

    List<PostDTO> selectPostList(PostFilterDTO listOpt);

    PostDTO selectPostInfoByPno(long pno);

    int insertPost(PostVO postVO);

    int updatePost(PostVO postVO);

    int deletePost(long pno);

    int deletePostByBoardNo(long boardNo);

    int deleteAllPosts();

    boolean isWriter(@Param("pno") long pno, @Param("userId") String userId);

}
