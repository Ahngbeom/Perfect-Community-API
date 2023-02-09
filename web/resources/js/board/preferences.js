import {getBoard, getBoardFormHTML} from "./board.js";
import {getCookieToJson, setCookie} from "../pageCookie.js";

$(document).on('click', "#boardPreferences", (e) => {
    const selectedBoardNo = getCookieToJson(POST_FILTER_OPTIONS_COOKIE_NAME);
    const boardData = getBoard(selectedBoardNo.boardNo);
    console.log(boardData);
    additionalArea.html("<h4>게시판 관리</h4>");
    additionalArea.append(getBoardFormHTML(boardData));
    additionalArea.toggleClass("visually-hidden");
});

$(document).on('click', "#boardForm #boardUpdateBtn", () => {
    const boardForm = $("#boardForm");
    const updateBoardNo = boardForm.data("bno");
    const updateBoardData = {
        title: boardForm.find("input[name='title']").val(),
        comment: boardForm.find("textarea[name='comment']").val()
    }
    console.log(updateBoardNo, updateBoardData);

    $.ajax({
        type: 'patch',
        url: '/api/board/' + updateBoardNo,
        data: JSON.stringify(updateBoardData)
    }).done(() => {
        location.reload();
    }).fail((jqXHR) => {
        alert(jqXHR.responseText);
    });
});

$(document).on('click', "#boardForm #boardClosureBtn", () => {
    const boardForm = $("#boardForm");
    const deleteBoardNo = boardForm.data("bno");

    $.ajax({
        type: 'delete',
        url: '/api/board/' + deleteBoardNo
    }).done(() => {
        if (getCookieToJson(POST_FILTER_OPTIONS_COOKIE_NAME).boardNo === deleteBoardNo) {
            setCookie(POST_FILTER_OPTIONS_COOKIE_NAME, {});
        }
        location.reload();
    }).fail((jqXHR) => {
        alert(jqXHR.responseText);
    });
});

