/*
 * Copyright (C) 23. 2. 4. 오전 2:55 Ahngbeom (https://github.com/Ahngbeom)
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

export let userData = {
    username: undefined,
    userRole: undefined,
    accessToken: undefined
}

export const POST_FILTER_OPTIONS_KEY = "post-filter-options";
export const PAGINATION_DATA_KEY = "pagination";

export const additionalButtonsArea = $("#additionalButtons");

const mainContents = $("#mainContents");

/* Authentication by JWT reissue. */
$(document).ready(() => {
    $.ajax({
        type: 'post',
        url: '/api/jwt/reissue',
        async: false,
        contentType: 'application/json',
        success: (data) => {
            userData.username = data.username;
            userData.userRole = data.authorities;
            userData.accessToken = data.accessToken;
            // putJwtInfo(data);
        }
    });
});
// import("./authentication/jwt.js");
// import("./pageCookie.js");
// import("./ajax.js");
// import("./authentication/authentication.js");
// import("./board/board.js");
// import("./post/list.js");
// import("./post/pagination.js");
// import("./post/details.js");
