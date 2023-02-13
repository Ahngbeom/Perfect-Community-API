import {putAuthentication} from "./authentication.js";
import {clearCookie} from "../pageCookie.js";

function reissueJWT() {
    return $.ajax({
        type: 'post',
        url: '/api/jwt/reissue',
        async: false,
    }).done((data) => {
        userData.username = data.username;
        userData.userRole = data.authorities;
        userData.accessToken = data.accessToken;
    }).fail(() => {
        clearCookie(JWT_REFRESH_TOKEN_COOKIE_NAME);
    }).always(putAuthentication);
}

export {reissueJWT};