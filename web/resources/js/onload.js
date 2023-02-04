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

import {reissueJWT} from "./authentication/jwt.js";
import {loadAuthentication} from "./authentication/authentication.js";

$(document).ajaxSend((event, jqXHR, ajaxOptions) => {
    if (!$.isEmptyObject(userData) && userData.accessToken !== undefined)
        jqXHR.setRequestHeader("Authorization", "Bearer " + userData.accessToken);
});

$(document).ajaxComplete((event, jqXHR, ajaxOptions) => {
    // console.log(event);
    // console.log(jqXHR);
    // console.log(ajaxOptions);
    if (jqXHR.status >= 400) {
        console.error(jqXHR.status, jqXHR.statusText, jqXHR.responseText);
        console.error(ajaxOptions);
    }
});

$.ajaxSetup({
    contentType: 'application/json; charset=utf-8',
    dataType: 'json'
});

/* Authentication by JWT reissue. */
reissueJWT();

$(document).on('click', loadAuthentication);