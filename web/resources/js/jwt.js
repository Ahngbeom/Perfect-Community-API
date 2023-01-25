let username = undefined;
let accessToken = undefined;

window.onload = () => {
    $.ajax({
        type: 'post',
        url: '/api/jwt/reissue',
        contentType: 'application/json',
        success: (data) => {
            console.log(data);
            username = data.username;
            accessToken = data.accessToken;
            putJwtInfo(data);
        }
    })
}

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

let accessTokenInterval;
let refreshTokenInterval;
function putJwtInfo(jwtData) {

    console.log(jwtData);
    console.log("Access Token Expiration", new Date(jwtData.accessTokenExpiration));
    console.log("Refresh Token Expiration", new Date(jwtData.refreshTokenExpiration));

    let accessTokenValidity = jwtData.accessTokenExpiration - Date.now();
    let refreshTokenValidity = jwtData.refreshTokenExpiration - Date.now();

    // $("#jwtInfo").html(accessTokenValidityMinutes + ":" + accessTokenValiditySeconds);

    accessTokenInterval = setInterval(() => {
        accessTokenValidity -= 1000;
        if (accessTokenValidity < 0) {
            $("#jwtInfo #accessTokenValidity #accessTokenValidityTimeLeft").html("만료").addClass("text-danger");
            clearInterval(accessTokenInterval);
        } else {
            const accessTokenValidityMinutes = Math.round(accessTokenValidity / 1000 / 60);
            const accessTokenValiditySeconds = Math.round(accessTokenValidity / 1000);

            // $("#jwtInfo").html(jwtData.accessTokenExpiration - Date.now());
            $("#jwtInfo #accessTokenValidity #accessTokenValidityTimeLeft").html(accessTokenValidityMinutes + ":" + (accessTokenValiditySeconds < 10 ? "0" + accessTokenValiditySeconds : accessTokenValiditySeconds));
        }
    }, 1000);

    refreshTokenInterval = setInterval(() => {
        refreshTokenValidity -= 1000;
        if (refreshTokenValidity < 0) {
            $("#jwtInfo #refreshTokenValidity #refreshTokenValidityTimeLeft").html("만료").addClass("text-danger");
            clearInterval(refreshTokenInterval);
        } else {
            const refreshTokenValidityMinutes = Math.round(refreshTokenValidity / 1000 / 60) - 1;
            const refreshTokenValiditySeconds = Math.round(refreshTokenValidity / 1000 % 60);

            // $("#jwtInfo").html(jwtData.accessTokenExpiration - Date.now());
            $("#jwtInfo #refreshTokenValidity #refreshTokenValidityTimeLeft").html(refreshTokenValidityMinutes + ":" + (refreshTokenValiditySeconds < 10 ? "0" + refreshTokenValiditySeconds : refreshTokenValiditySeconds));
        }
    }, 1000);

}