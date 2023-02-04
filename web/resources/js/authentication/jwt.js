/*
 * Copyright (C) 23. 2. 4. 오후 8:58 Ahngbeom (https://github.com/Ahngbeom)
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

import {loadAuthentication} from "./authentication.js";

function reissueJWT() {
    return $.ajax({
        type: 'post',
        url: '/api/jwt/reissue',
        async: false,
    }).done((data) => {
        userData.username = data.username;
        userData.userRole = data.authorities;
        userData.accessToken = data.accessToken;
    }).always(loadAuthentication);
}

export {reissueJWT};


export let accessTokenInterval;
export let refreshTokenInterval;

function putJwtInfo(jwtData) {
    console.log("Access Token Expiration", new Date(jwtData.accessTokenExpiration));
    console.log("Refresh Token Expiration", new Date(jwtData.refreshTokenExpiration));

    // let accessTokenValidity = jwtData.accessTokenExpiration - Date.now();
    // let refreshTokenValidity = jwtData.refreshTokenExpiration - Date.now();

    // accessTokenInterval = setInterval(() => {
    //     accessTokenValidity -= 1000;
    //     if (accessTokenValidity < 0) {
    //         $("#jwtInfo #accessTokenValidity #accessTokenValidityTimeLeft").html("만료").addClass("text-danger");
    //         clearInterval(accessTokenInterval);
    //     } else {
    //         const accessTokenValidityMinutes = Math.round(accessTokenValidity / 1000 / 60);
    //         const accessTokenValiditySeconds = Math.round(accessTokenValidity / 1000);
    //
    //         // $("#jwtInfo").html(jwtData.accessTokenExpiration - Date.now());
    //         $("#jwtInfo #accessTokenValidity #accessTokenValidityTimeLeft").html(accessTokenValidityMinutes + ":" + (accessTokenValiditySeconds < 10 ? "0" + accessTokenValiditySeconds : accessTokenValiditySeconds));
    //     }
    // }, 1000);
    //
    // refreshTokenInterval = setInterval(() => {
    //     refreshTokenValidity -= 1000;
    //     if (refreshTokenValidity < 0) {
    //         $("#jwtInfo #refreshTokenValidity #refreshTokenValidityTimeLeft").html("만료").addClass("text-danger");
    //         clearInterval(refreshTokenInterval);
    //     } else {
    //         const refreshTokenValidityMinutes = Math.round(refreshTokenValidity / 1000 / 60) - 1;
    //         const refreshTokenValiditySeconds = Math.round(refreshTokenValidity / 1000 % 60);
    //
    //         // $("#jwtInfo").html(jwtData.accessTokenExpiration - Date.now());
    //         $("#jwtInfo #refreshTokenValidity #refreshTokenValidityTimeLeft").html(refreshTokenValidityMinutes + ":" + (refreshTokenValiditySeconds < 10 ? "0" + refreshTokenValiditySeconds : refreshTokenValiditySeconds));
    //     }
    // }, 1000);

}

export {putJwtInfo};