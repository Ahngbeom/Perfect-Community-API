package com.perfect.community.api.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perfect.community.api.dto.post.PostDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserViewedPostsDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime viewDate;

    PostDTO post;
}
