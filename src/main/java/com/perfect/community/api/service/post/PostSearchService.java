package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostFilterDTO;

import java.util.List;

public interface PostSearchService {
    List<PostDTO> searchPostByKeyword(List<PostFilterDTO> searchConditions);
    List<PostDTO> searchPostByRegex(List<String> searchConditionsRegex);
}
