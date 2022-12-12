package com.board.api.controller.board;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.board.api.DTO.PostsDTO;
import com.board.api.DTO.PostsSearchDTO;
import com.board.api.service.board.PostsSearchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("")
public class PostsSearchController {

    private static final Logger log = LogManager.getLogger();
    private final PostsSearchService postsSearchService;

    @PostMapping("/api/board/search")
    public ResponseEntity<?> searchPosts(@RequestBody PostsSearchDTO searchDTO) {
        log.info(searchDTO);
        List<PostsDTO> postsSearchResult;
        try {
            postsSearchResult = postsSearchService.searchBoardByKeyword(searchDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(postsSearchResult);
    }
}
