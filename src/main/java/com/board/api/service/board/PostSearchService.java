package com.board.api.service.board;

import com.board.api.DTO.PostDTO;
import com.board.api.DTO.PostSearchDTO;

import java.util.List;

public interface PostSearchService {
    List<PostDTO> searchPostByKeyword(List<PostSearchDTO> searchDTO);
}
