package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostExtractionDTO;

import java.util.List;

public interface PostSearchService {
    List<PostDTO> searchPostByKeyword(List<PostExtractionDTO> searchConditions);
    List<PostDTO> searchPostByRegex(List<String> searchConditionsRegex);
}
