import {addButtonsByUserRole} from "../user/user.js";

const isAuthenticatedElem = $("#isAuthenticated");
const isAnonymousElem = $("#isAnonymous");
const loginBtn = $("#loginBtn");
const logoutBtn = $("#logoutBtn");

function putAuthentication() {
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
        additionalButtonsArea.removeClass("visually-hidden");
    } else {
        isAuthenticatedElem.addClass("visually-hidden");
        isAnonymousElem.removeClass("visually-hidden");
        additionalButtonsArea.addClass("visually-hidden");
    }

    addButtonsByUserRole(userData.userRole);

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
        }),
        success: (data, status, xhr) => {
            userData.username = data.username;
            userData.userRole = data.authorities;
            userData.accessToken = xhr.getResponseHeader("Authorization").substring("Bearer ".length);
            putAuthentication();
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
        console.log("Logout", data);
        userData = {};
        // clearInterval(accessTokenInterval);
        // clearInterval(refreshTokenInterval);
    }).fail((xhr) => {
        console.error(xhr);
        console.error(xhr.responseText);
    }).always(() => {
        putAuthentication();
    });
});

export {putAuthentication};