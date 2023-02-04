/*
 * Copyright (C) 23. 2. 5. 오전 12:07 Ahngbeom (https://github.com/Ahngbeom)
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

import {getCookieToJson} from "../pageCookie.js";

/* Add buttons by user role */
function addButtonsByUserRole(userRoles) {
    if ($.inArray('ROLE_ADMIN', userRoles) >= 0) {
        // additionalButtonsArea.append("<button type='button' class='btn btn-sm btn-outline-secondary' id='boardPreferences'>게시판 관리</button>");
        if (getCookieToJson(POST_FILTER_OPTIONS_KEY).boardNo !== undefined)
            $("#boardControlButtons").html("<button type='button' class='btn btn-sm btn-outline-secondary' id='boardPreferences'>게시판 관리</button>");
        else
            $("#boardControlButtons").html("");
    } else if ($.inArray('ROLE_MANAGER', userRoles) >= 0) {

    } else if ($.inArray('ROLE_USER', userRoles) >= 0) {
        $("#boardControlButtons").html("");
    } else {
        $("#boardControlButtons").html("");
    }
}

export {addButtonsByUserRole}