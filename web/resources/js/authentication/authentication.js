/*
 * Copyright (C) 23. 2. 3. 오후 6:38 Ahngbeom (https://github.com/Ahngbeom)
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

const logoutBtn = $("#logoutBtn");

loadAuthentication();

function loadAuthentication() {
    // console.log("Username", username);
    // console.log("Access Token", accessToken);
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
        userData.accessToken = undefined;
        // clearInterval(accessTokenInterval);
        // clearInterval(refreshTokenInterval);
    }).fail((xhr) => {
        console.error(xhr);
        console.error(xhr.responseText);
    }).always((jqXHR) => {
        location.reload();
    });
});

export {loadAuthentication};