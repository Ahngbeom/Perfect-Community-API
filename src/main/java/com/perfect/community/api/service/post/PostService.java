package com.perfect.community.api.service.post;

import com.google.common.base.Preconditions;
import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostFilterDTO;
import com.perfect.community.api.mapper.post.PostInteractionMapper;
import com.perfect.community.api.mapper.post.PostMapper;
import com.perfect.community.api.vo.post.PostVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;
    private final PostInteractionMapper postInteractionMapper;
    private final PostUtils postUtils;

    public List<PostDTO> getPostList(PostFilterDTO postListOptions) {
        return postMapper.selectPostList(postListOptions);
    }

    public PostDTO getInfoByPno(long pno) {
        Preconditions.checkState(pno > 0, "Invalid post no.");
        PostDTO postDTO = postMapper.selectPostInfoByPno(pno);
        if (postDTO == null)
            return null;
//        PostDTO postDTO = postDTO.toDTO();
        postDTO.setViews(postInteractionMapper.getViews(pno));
        postDTO.setRecommend(postInteractionMapper.getRecommend(pno));
        postDTO.setNotRecommend(postInteractionMapper.getNotRecommend(pno));
        return postDTO;
    }

    public long registration(String userId, PostDTO postDTO) throws RuntimeException {
        /*  PostDTO fields Validate */
        validatePostDTO(postDTO);
        postDTO.setWriter(Preconditions.checkNotNull(userId, "[writer] must not be null."));

        /* Register and initialize views, recommend, not recommend */
        PostVO postVO = new PostVO(postDTO);
        if (postMapper.insertPost(postVO) != 1)
            throw new RuntimeException("Failed to post registration.");
        if (postInteractionMapper.initializeViews(postVO.getPost_no()) != 1)
            throw new RuntimeException("Failed to post views initialization.");
        if (postInteractionMapper.initializeRecommend(postVO.getPost_no()) != 1)
            throw new RuntimeException("Failed to post recommend initialization.");
        return postVO.getPost_no();
    }

    public long modification(long postNo, PostDTO postDTO) {
        /*  PostDTO fields Validate */
        postDTO.setPostNo(postNo);
        validatePostDTO(postDTO);

        /* Register and initialize views, recommend, not recommend */
        PostVO postVO = new PostVO(postDTO);
        if (postMapper.updatePost(postVO) != 1) {
            throw new RuntimeException("Failed to post modification.");
        }
        return postVO.getPost_no();
    }

    public void remove(long pno) {
        Preconditions.checkState(pno > 0, "Invalid post no.");
//        Preconditions.checkState(postUtils.checkPostVerification(userId, pno), "You do not have permission to remove the post.");
        postMapper.deletePost(pno);
    }

    public boolean isWriter(long pno, String userId) {
        return postMapper.isWriter(pno, userId);
    }


    public long getPostCount(PostFilterDTO postFilterDTO) {
        return postMapper.countPosts(postFilterDTO);
    }

    public void validatePostDTO(PostDTO postDTO) {
        Preconditions.checkNotNull(postDTO, "PostDTO must not be null.");
//        Preconditions.checkState(postDTO.getPostNo() > 0, "Invalid post no. [pno] must be greater than zero.");
        Preconditions.checkState(postDTO.getBoardNo() > 0, "Invalid board no. [boardNo] must be greater than zero.");
//        Preconditions.checkNotNull(postDTO.getWriter(), "[writer] must not be null.");
        Preconditions.checkNotNull(postDTO.getType(), "[type] must not be null.");
        Preconditions.checkNotNull(postDTO.getTitle(), "[title] must not be null.");
        Preconditions.checkNotNull(postDTO.getContents(), "[contents] must not be null.");
    }

}
