/*
 * Copyright (C) 23. 2. 4. 오후 8:53 Ahngbeom (https://github.com/Ahngbeom)
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

package com.perfect.community.api.controller.jwt;

import com.google.common.base.Preconditions;
import com.perfect.community.api.dto.jwt.TokenDTO;
import com.perfect.community.api.security.jwt.JwtTokenProvider;
import com.perfect.community.api.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
public class JwtController {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtService jwtService;

//    @GetMapping
//    public ResponseEntity<String> Jwt(HttpServletRequest request) {
//        return ResponseEntity.ok(request.getHeader("Authorization"));
//    }

    @PostMapping("/reissue")
    public ResponseEntity<?> JwtAuthentication(HttpServletRequest request/*, @RequestBody Map<String, String> requestBody*/) {
        try {
            String refreshToken = jwtService.resolveRefreshToken(request);
            Preconditions.checkState(refreshToken != null && !refreshToken.isEmpty(), "Invalid JWT.");
            TokenDTO tokenDTO = jwtService.reissue(refreshToken);
            if (tokenDTO != null) {
                log.info("[SUCCESS] Reissued JWT");
                return ResponseEntity.ok(tokenDTO);
            } else
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Failed");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getClass().getSimpleName() + " - {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
