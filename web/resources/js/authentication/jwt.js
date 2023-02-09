import {putAuthentication} from "./authentication.js";

function reissueJWT() {
    return $.ajax({
        type: 'post',
        url: '/api/jwt/reissue',
        async: false,
    }).done((data) => {
        userData.username = data.username;
        userData.userRole = data.authorities;
        userData.accessToken = data.accessToken;
    }).always(putAuthentication);
}

export {reissueJWT};