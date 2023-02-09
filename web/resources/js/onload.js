import {reissueJWT} from "./authentication/jwt.js";
import {getBoardList, putBoardList} from "./board/board.js";
import {putPostList} from "./post/list.js";

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

/* Page initialization */
// window.onload = () => {
reissueJWT();
putBoardList(getBoardList());
putPostList();
// }