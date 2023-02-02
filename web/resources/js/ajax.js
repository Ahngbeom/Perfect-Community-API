/*
 * Copyright (C) 2023 Ahngbeom (bbu0704@gmail.com)
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

$(document).ajaxSend((event, jqXHR, ajaxOptions) => {
    if (accessToken !== undefined && accessToken !== null && accessToken !== "")
        jqXHR.setRequestHeader("Authorization", "Bearer " + accessToken);
});

$(document).ajaxComplete((event, jqXHR, ajaxOptions) => {
    // console.log(event);
    // console.log(jqXHR);
    // console.log(ajaxOptions);
    if (jqXHR.status >= 400) {
        console.error(jqXHR.status, jqXHR.statusText, jqXHR.responseText);
        console.error();
    }
    loadAuthentication();
});

$.ajaxSetup({
    contentType: 'application/json; chrset=utf-8',
    dataType: 'json'
});