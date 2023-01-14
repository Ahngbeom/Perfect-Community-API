package com.perfect.community.api.mapper.user;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.vo.post.PostVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostScrapMapper {

    int insertScrapPost(@Param("userId") String userId, @Param("postNo") long postNo);

    List<PostDTO> getAllByUserId(String userId);

    int deleteByUserIdAndPostNo(@Param("userId") String userId, @Param("postNo") long postNo);
}
