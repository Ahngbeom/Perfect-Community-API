package com.perfect.community.api.service.post;

import com.google.common.base.Preconditions;
import com.perfect.community.api.dto.post.PostFilterDTO;
import com.perfect.community.api.mapper.post.PostInteractionMapper;
import com.perfect.community.api.vo.post.PostVO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.mapper.post.PostMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private static final Logger log = LogManager.getLogger(PostServiceImpl.class);
    private final PostMapper postMapper;
    private final PostInteractionMapper postInteractionMapper;
    private final PostUtils postUtils;

    @Override
    public List<PostDTO> getPostList(PostFilterDTO postListOptions) {
        List<PostVO> postVoList = postMapper.selectPostList(postListOptions);
        return postVoList.stream().map(PostVO::toDTO).collect(Collectors.toList());
    }

    @Override
    public PostDTO getInfoByPno(long pno) {
        Preconditions.checkState(pno > 0, "Invalid post no.");
        PostVO postVO = postMapper.selectPostInfoByPno(pno);
        return postVO != null ? postVO.toDTO() : null;
    }

    @Override
    public void registration(String userId, PostDTO postDTO) throws RuntimeException {
        Preconditions.checkNotNull(postDTO, "PostDTO must not be null.");
        Preconditions.checkState(postDTO.getBoardNo() > 0, "Invalid board no. [boardNo] must be greater than zero.");
        Preconditions.checkState(postDTO.getType() != null && !postDTO.getType().isEmpty(), "[type] must not be null or empty.");
        Preconditions.checkState(postDTO.getTitle() != null && !postDTO.getTitle().isEmpty(), "[title] must not be null or empty.");
        Preconditions.checkState(postDTO.getContents() != null && !postDTO.getContents().isEmpty(), "[contents] must not be null or empty.");
        postDTO.setWriter(Preconditions.checkNotNull(userId, "[writer] must not be null."));
        if (postMapper.insertPost(postDTO) != 1)
            throw new RuntimeException("Failed to post registration.");
        if (postInteractionMapper.initializeViews(postDTO.getPostNo()) != 1)
            throw new RuntimeException("Failed to post views initialization.");
        if (postInteractionMapper.initializeRecommend(postDTO.getPostNo()) != 1)
            throw new RuntimeException("Failed to post recommend initialization.");
//        log.info(postDTO);
    }

    @Override
    public void modification(long postNo, String userId, PostDTO postDTO) {
        Preconditions.checkState(postNo > 0, "Invalid post no. [pno] must be greater than zero.");
        postDTO.setPostNo(postNo);
        Preconditions.checkState(postDTO.getBoardNo() > 0, "Invalid board no. [boardNo] must be greater than zero.");
        Preconditions.checkState(postDTO.getType() != null, "[type] must not be null.");
        Preconditions.checkState(postDTO.getTitle() != null, "[title] must not be null.");
        Preconditions.checkState(postDTO.getContents() != null, "[contents] must not be null.");
        Preconditions.checkState(userId != null, "[contents] must not be null.");
        postDTO.setWriter(userId);
        if (postMapper.updatePost(postDTO) != 1) {
            throw new RuntimeException("Failed to post modification.");
        }
    }

    @Override
    public void remove(String userId, long pno) {
        Preconditions.checkState(pno > 0, "Invalid post no.");
        Preconditions.checkState(postUtils.checkPostVerification(userId, pno), "You do not have permission to remove the post.");
        postMapper.deletePost(pno);
    }

    @Override
    public boolean isWriter(long pno, String userId) {
        return postMapper.isWriter(pno, userId);
    }


    @Override
    public long getPostCount(PostFilterDTO postFilterDTO) {
        return postMapper.countPosts(postFilterDTO);
    }

}
