import {reissueJWT} from "./authentication/jwt.js";
import {getCookieToJson} from "./pageCookie.js";
import {getPost, putPostDetailsForm} from "./post/details.js";
import {putAuthentication} from "./authentication/authentication.js";

$(document).ajaxSend((event, jqXHR, ajaxOptions) => {
    if (!$.isEmptyObject(userData) && userData.accessToken !== undefined)
        jqXHR.setRequestHeader("Authorization", "Bearer " + userData.accessToken);
});

$(document).ajaxComplete((event, jqXHR, ajaxOptions) => {
    // console.log(event);
    // console.log(jqXHR);
    // console.log(ajaxOptions);
    if (jqXHR.status >= 400) {
        console.error(jqXHR.status, jqXHR.statusText, jqXHR.responseText);
        console.error(ajaxOptions);
    }
});

$.ajaxSetup({
    contentType: 'application/json; charset=utf-8',
    dataType: 'json'
});

function reloadLayout() {
    putAuthentication();

    const postDetailsData = getCookieToJson(POST_DETAILS_COOKIE_NAME);
    if (!$.isEmptyObject(postDetailsData))
        putPostDetailsForm(getPost(postDetailsData.postNo));
}

/* Page initialization */
// window.onload = () => {
reissueJWT();
// }

export {reloadLayout};