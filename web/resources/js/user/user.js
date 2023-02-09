import {getCookieToJson} from "../pageCookie.js";

/* Add buttons by user role */
function addButtonsByUserRole(userRoles) {
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
}

$("#accountPreferencesBtn").on('click', () => {
    location.href = "/user";
});

export {addButtonsByUserRole}