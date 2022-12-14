package com.board.api.service.post;

import com.board.api.dto.post.PostDTO;
import com.board.api.dto.post.PostSearchDTO;

import java.util.List;

public interface PostSearchService {
    List<PostDTO> searchPostByKeyword(List<PostSearchDTO> searchConditions);
    List<PostDTO> searchPostByRegex(List<String> searchConditionsRegex);
}
