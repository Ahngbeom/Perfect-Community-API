package com.board.api.service.board;

import com.board.api.dto.PostDTO;
import com.board.api.dto.PostSearchDTO;

import java.util.List;

public interface PostSearchService {
    List<PostDTO> searchPostByKeyword(List<PostSearchDTO> searchConditions);
    List<PostDTO> searchPostByRegex(List<String> searchConditionsRegex);
}
