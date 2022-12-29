package com.perfect.community.api.dto.user;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class UserAuthoritiesDTO {

    private String userId;
    private List<String> authorities;

    @Builder
    public UserAuthoritiesDTO(String userId, List<String> authorities) {
        this.userId = userId;
        this.authorities = authorities;
    }

    //    @Getter
//    @ToString
//    @NoArgsConstructor
//    public static class Request {
//        private String userId;
//        private List<String> authorities;
//
//        @Builder
//        public Request(String userId, List<String> authorities) {
//            this.userId = userId;
//            this.authorities = authorities;
//        }
//    }
//
//    @Getter
//    @ToString
//    public static class Response {
//        private final UserDTO userId;
//        private final List<AuthoritiesDTO> authorities;
//
//        @Builder
//        public Response(UserDTO userId, List<AuthoritiesDTO> authorities) {
//            this.userId = userId;
//            this.authorities = authorities;
//        }
//    }
}