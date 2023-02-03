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

import {additionalButtonsArea, userData} from "../global_variable.js";

const logoutBtn = $("#logoutBtn");

loadAuthentication();

function loadAuthentication() {
    console.log(userData);
    /* Authentication on top */
    if (userData.username !== undefined && userData.accessToken !== undefined) {
        // $("#authentication span").html("Authenticated (" + username + ")").css("color", "green");
        if ($.inArray('ROLE_ADMIN', userData.userRole) >= 0) {
            $("#authenticatedUsername").html("ID: " + userData.username).css("color", "goldenrod");
        } else {
            $("#authenticatedUsername").html("ID: " + userData.username).css("color", "green");
        }
        $("#authentication").removeClass("visually-hidden");
        $("#loginForm").addClass("visually-hidden");
        logoutBtn.removeClass("visually-hidden");
    } else {
        // $("#authentication span").html("");
        $("#authentication").addClass("visually-hidden");
        $("#loginForm").removeClass("visually-hidden");
        logoutBtn.addClass("visually-hidden");
    }

    /* Add control button by user role */
    additionalButtonsArea.html("");
    if ($.inArray('ROLE_ADMIN', userData.userRole)) {
        additionalButtonsArea.append("<button type='button' class='btn btn-sm btn-outline-secondary'>게시판 관리</button>");
    }
    additionalButtonsArea.append("<button type='button' class='btn btn-sm btn-outline-secondary'>계정 정보 수정</button>");

}

$("#loginBtn").on('click', () => {
    $.ajax({
        type: 'post',
        url: '/api/login',
        async: false,
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
            username: $("input[name='username']").val(),
            password: $("input[name='password']").val()
        }),
        success: (data, status, xhr) => {
            userData.username = data.username;
            userData.accessToken = xhr.getResponseHeader("Authorization").substring("Bearer ".length);
            loadAuthentication();
            // putJwtInfo(data);
        },
        error: (xhr) => {
            alert(xhr.responseText);
        }
    })
});

logoutBtn.on('click', () => {
    $.ajax({
        type: 'post',
        url: '/api/logout',
        async: false,
        contentType: 'application/json',
    }).done((data) => {
        console.log(data);
        userData.username = undefined;
        userData.userRole = undefined;
        userData.accessToken = undefined;
        // clearInterval(accessTokenInterval);
        // clearInterval(refreshTokenInterval);
    }).fail((xhr) => {
        console.error(xhr);
        console.error(xhr.responseText);
    }).always((jqXHR) => {
        loadAuthentication();
    });
});

export {loadAuthentication};