package com.perfect.community.api.mapper.user;

import com.perfect.community.api.dto.post.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Mapper
public interface UserPostViewsMapper {

	List<PostDTO> getRecentlyViewedPosts(@Param("userId") String userId, @Param("amount") long amount);
	LocalDateTime getPostViewedDate(@Param("userId") String userId, @Param("postNo") long pno);

	int addViewedPost(@Param("userId") String userId, @Param("postNo") long pno);
	int updateViewedPost(@Param("userId") String userId, @Param("postNo") long pno);

}
