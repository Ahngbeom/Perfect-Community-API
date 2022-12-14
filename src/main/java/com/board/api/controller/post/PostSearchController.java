package com.board.api.controller.post;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.board.api.dto.post.PostDTO;
import com.board.api.dto.post.PostSearchDTO;
import com.board.api.service.post.PostSearchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("")
public class PostSearchController {

    private static final Logger log = LogManager.getLogger();
    private final PostSearchService postSearchService;

    @GetMapping("/api/board/search")
    public ResponseEntity<?> searchPosts(@RequestBody List<PostSearchDTO> searchConditions) {
        List<PostDTO> postsSearchResult;
        try {
            postsSearchResult = postSearchService.searchPostByKeyword(searchConditions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(postsSearchResult);
    }

    @GetMapping("/api/board/search/regex")
    public ResponseEntity<?> searchPostsByRegex(@RequestBody List<String> searchConditionsRegex) {
        List<PostDTO> postsSearchResult;
        try {
            postsSearchResult = postSearchService.searchPostByRegex(searchConditionsRegex);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(postsSearchResult);
    }
}
