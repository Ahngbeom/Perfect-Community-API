/*
 * Copyright (C) 23. 2. 3. 오후 6:40 Ahngbeom (https://github.com/Ahngbeom)
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

package com.perfect.community.api.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String userId;
    private String password;
    private String nickname;
    private boolean enabled;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private String authority;

    @Builder
    public UserDTO(String userId, String password, String nickname, boolean enabled, LocalDateTime regDate, LocalDateTime updateDate, String authority) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.enabled = enabled;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.authority = authority;
    }

    @Override
    public String toString() {
        return this.authority != null && !this.authority.isEmpty()
                ? "UserDTO{" +
                "userId='" + userId + '\'' +
                ", password=[PROTECTED]" +
                ", nickname='" + nickname + '\'' +
                ", enabled=" + enabled +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", authority=" + authority +
                '}'
                : "UserDTO{" +
                "userId='" + userId + '\'' +
                ", password=[PROTECTED]" +
                ", nickname='" + nickname + '\'' +
                ", enabled=" + enabled +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
