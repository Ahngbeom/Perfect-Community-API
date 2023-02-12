import {reloadLayout} from "../onload.js";
import {getCookieToJson} from "../pageCookie.js";

const isAuthenticatedElem = $("#isAuthenticated");
const isAnonymousElem = $("#isAnonymous");
const loginBtn = $("#loginBtn");
const logoutBtn = $("#logoutBtn");

function putAuthentication() {
    $(document).ready(() => {
        console.log("userData", userData);

        /* Authentication on top */
        if (!$.isEmptyObject(userData)) {
            if ($.inArray('ROLE_ADMIN', userData.userRole) >= 0) {
                isAuthenticatedElem.find("span").text("ID: " + userData.username).css("color", "goldenrod");
            } else {
                isAuthenticatedElem.find("span").text("ID: " + userData.username).css("color", "green");
            }
            isAuthenticatedElem.removeClass("visually-hidden");
            isAnonymousElem.addClass("visually-hidden");
            $("#additionalButtons #accessRestrictedButtons").removeClass("visually-hidden");
        } else {
            isAuthenticatedElem.addClass("visually-hidden");
            isAnonymousElem.removeClass("visually-hidden");
            $("#additionalButtons #accessRestrictedButtons").addClass("visually-hidden");
        }
        addButtonsByUserRole(userData.userRole);
    });
}

/* Add buttons by user role */
function addButtonsByUserRole(userRoles) {
    $(document).ready(() => {
        if ($.inArray('ROLE_ADMIN', userRoles) >= 0) {
            // additionalButtonsArea.append("<button type='button' class='btn btn-sm btn-outline-secondary' id='boardPreferences'>게시판 관리</button>");
            $("#boardControlButtonsOnSideBar").html("<button type=\"button\" class=\"btn btn-sm btn-outline-secondary rounded-0\" id=\"showBoardCreateFormBtn\">게시판 생성</button>");
            if (getCookieToJson(POST_FILTER_OPTIONS_COOKIE_NAME).boardNo !== undefined)
                $("#boardControlButtonsOnMain").html("<button type='button' class='btn btn-sm btn-outline-secondary rounded-0' id='boardPreferences'>게시판 관리</button>");
            else
                $("#boardControlButtonsOnMain").html("");
        } else if ($.inArray('ROLE_MANAGER', userRoles) >= 0) {

        } else if ($.inArray('ROLE_USER', userRoles) >= 0) {
            $("#boardControlButtonsOnSideBar").html("");
            $("#boardControlButtonsOnMain").html("");
        } else {
            $("#boardControlButtonsOnSideBar").html("");
            $("#boardControlButtonsOnMain").html("");
        }
    });
}

loginBtn.on('click', () => {
    $.ajax({
        type: 'post',
        url: '/api/login',
        async: false,
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
            username: $("input[name='username']").val(),
            password: $("input[name='password']").val()
        })
    }).done((data, status, xhr) => {
        userData.username = data.username;
        userData.userRole = data.authorities;
        userData.accessToken = xhr.getResponseHeader("Authorization").substring("Bearer ".length);
        putAuthentication();
        // putJwtInfo(data);
    }).fail((xhr) => {
        alert(xhr.responseText);
    }).always(reloadLayout)
});

logoutBtn.on('click', () => {
    $.ajax({
        type: 'post',
        url: '/api/logout',
        async: false,
        contentType: 'application/json',
    }).done((data) => {
        console.log("Logout", data);
        userData = {};
        // clearInterval(accessTokenInterval);
        // clearInterval(refreshTokenInterval);
    }).fail((xhr) => {
        console.error(xhr);
        console.error(xhr.responseText);
    }).always(reloadLayout);
});

export {putAuthentication, addButtonsByUserRole};