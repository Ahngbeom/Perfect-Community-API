package com.perfect.community.api.service.post;

import com.perfect.community.api.mapper.post.PostMapper;
import com.perfect.community.api.mapper.post.PostTypeMapper;
import com.perfect.community.api.mapper.utils.UtilsMapper;
import org.springframework.stereotype.Service;

@Service
public class PostUtils {
    private final PostMapper postMapper;
    private final PostTypeMapper postTypeMapper;
    private final UtilsMapper utilsMapper;

    public PostUtils(PostMapper postMapper, PostTypeMapper postTypeMapper, UtilsMapper utilsMapper) {
        this.postMapper = postMapper;
        this.postTypeMapper = postTypeMapper;
        this.utilsMapper = utilsMapper;
    }

    public long countPosts(long boardNo) {
        return postMapper.countPosts(boardNo);
    }

    public boolean checkPostVerification(String userId, long pno) {
        return userId.equals(postMapper.selectPostInfoByPno(pno).getWriter());
    }

    public boolean verifyPostType(String type) {
        return type == null || postTypeMapper.selectAllPostType().contains(type.toUpperCase());
    }

    public long initPnoValue() {
        return utilsMapper.initializeAutoIncrement("posts");
    }

    public int removeAll() throws RuntimeException {
        int result;
        if (countPosts(1) == 0) {
            throw new RuntimeException("Posts do not exist.");
        } else {
            result = postMapper.deleteAllPosts();
            if (result == 0)
                throw new RuntimeException("Failed to delete all posts");
            if (initPnoValue() == 0)
                throw new RuntimeException("Failure to initialize the bno value of the board");
        }
        return result;
    }
}
