package com.perfect.community.api.mapper.user;

import com.perfect.community.api.vo.post.PostVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostScrapMapper {

    int insertScrapPost(@Param("userId") String userId, @Param("postNo") long postNo);

    List<PostVO> getAllByUserId(String userId);

    int deleteByUserIdAndPostNo(@Param("userId") String userId, @Param("postNo") long postNo);
}
