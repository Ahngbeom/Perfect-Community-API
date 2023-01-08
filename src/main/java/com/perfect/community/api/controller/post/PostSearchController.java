package com.perfect.community.api.controller.post;

import com.perfect.community.api.dto.post.PostDTO;
import com.perfect.community.api.dto.post.PostFilterDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.perfect.community.api.service.post.PostSearchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class PostSearchController {

    private static final Logger log = LogManager.getLogger();
    private final PostSearchService postSearchService;

    @GetMapping("/")
    public ResponseEntity<?> searchPosts(@RequestBody List<PostFilterDTO> searchConditions) {
        List<PostDTO> postsSearchResult;
        try {
            postsSearchResult = postSearchService.searchPostByKeyword(searchConditions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(postsSearchResult);
    }

    @GetMapping("/regex")
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
