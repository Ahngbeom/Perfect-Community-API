/*
 * Copyright (C) 23. 2. 4. 오후 11:57 Ahngbeom (https://github.com/Ahngbeom)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.perfect.community.api.controller.board;

import com.perfect.community.api.dto.board.BoardDTO;
import com.perfect.community.api.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService service;

    @Cacheable("boards")
    @GetMapping
    public ResponseEntity<List<BoardDTO>> getBoardList() {
        return ResponseEntity.ok(service.getBoardList());
    }

    @GetMapping({"/{boardNo}"})
    public ResponseEntity<?> getBoardInfo(@PathVariable long boardNo) {
        try {
            BoardDTO boardDTO = service.getBoardInfo(boardNo);
            return ResponseEntity.ok(boardDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CacheEvict("boards")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> createBoard(Authentication authentication, @RequestBody BoardDTO boardDTO) {
        try {
            log.warn("{}", authentication);
            return ResponseEntity.ok(service.createBoard(authentication.getName(), boardDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CacheEvict("boards")
    @PatchMapping("/{boardNo}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateBoard(Principal principal, @PathVariable long boardNo, @RequestBody BoardDTO boardDTO) {
        try {
            service.updateBoard(principal.getName(), boardNo, boardDTO);
            return ResponseEntity.ok(boardNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CacheEvict("boards")
    @DeleteMapping("/{boardNo}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> deleteBoard(Principal principal, @PathVariable long boardNo) {
        try {
            service.deleteBoard(principal.getName(), boardNo);
            return ResponseEntity.ok(boardNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
