package com.board.api.service.board;

import com.board.api.DTO.PostsDTO;
import com.board.api.DTO.PostsSearchDTO;

import java.util.List;

public interface PostsSearchService {
    List<PostsDTO> searchBoardByKeyword(PostsSearchDTO searchVO);
}
