package com.perfect.community.api.mapper.user;

import com.perfect.community.api.dto.post.PostDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserScrapPostMapper {

    int insertScrapPost(@Param("userId") String userId, @Param("postNo") long postNo);

    List<PostDTO> getAllByUserId(String userId);

    int deleteByUserIdAndPostNo(@Param("userId") String userId, @Param("postNo") long postNo);
}
