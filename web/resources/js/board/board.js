import {putPostList} from "../post/list.js";
import {setCookie} from "../pageCookie.js";
import {addButtonsByUserRole} from "../authentication/authentication.js";

function getBoardList() {
    let result = null;
    $.ajax({
        type: 'get',
        url: '/api/board',
        async: false
    }).done((data) => {
        result = data;
    });
    return result;
}

function getBoard(bno) {
    if (bno === undefined)
        return null;
    let result = null;
    $.ajax({
        type: 'get',
        url: '/api/board/' + bno,
        async: false
    }).done((data) => {
        result = data;
    });
    return result;
}

function putBoardList(boards) {
    boards.forEach(board => {
        $("#boardList ul").append(
            "<li>" +
            "<button type='button' class='btn btn-link board-title' data-bno='" + board.bno + "'>" +
            board.title +
            "</button>" +
            "</li>")
        ;
    });
}

$(document).on('click', ".board-title", (e) => {
    const postFilterOptions = {
        boardNo: $(e.target).data('bno'),
        type: undefined,
        keyword: undefined,
        page: undefined
    }

    $.ajax({
        type: 'get',
        url: '/api/post/count',
        async: false,
        contentType: 'application/json',
        dataType: 'json',
        data: postFilterOptions
    }).done((result) => {
        /* Put posts count */
        // $("#postCount").text("(총 게시물 수: " + result + ")");

        setCookie(POST_FILTER_OPTIONS_COOKIE_NAME, postFilterOptions);

        /* Set pagination */
        setCookie(PAGINATION_DATA_COOKIE_NAME, {
            pageAmount: Number(result),
            activatedPage: 1
        });
    });
    addButtonsByUserRole(userData.userRole);
    putPostList();
});

$(document).on('click', "#showBoardCreateFormBtn", () => {
    additionalArea.html("<h4>게시판 생성</h4>");
    additionalArea.append(getBoardFormHTML());
    additionalArea.find("#boardForm input[name='createUser']").val(userData.username).attr("disabled", true);
    additionalArea.toggleClass("visually-hidden");
});

$(document).on('click', "#boardCreateBtn", () => {
    const boardForm = $("#boardForm");
    const createBoardData = {
        title: boardForm.find("input[name='title']").val(),
        comment: boardForm.find("textarea[name='comment']").val()
    }
    console.log(createBoardData);

    $.ajax({
        type: 'post',
        url: '/api/board',
        data: JSON.stringify(createBoardData)
    }).done(() => {
        location.reload();
    })
});

function getBoardFormHTML(boardData) {
    if (boardData !== undefined) {
        return "<form class='d-flex flex-column gap-3 p-3' id='boardForm' data-bno='" + boardData.bno + "'>" +
            "<div class=\"\">\n" +
            "  <label class=\"form-label\">Bulletin board created user</label>\n" +
            "  <input type=\"text\" class=\"form-control\" name='createUser' value='" + boardData.createUser + "'>\n" +
            "</div>\n" +
            "<div class=\"\">\n" +
            "  <label class=\"form-label\">Title</label>\n" +
            "  <input type=\"text\" class=\"form-control\" name='title' value='" + boardData.title + "'>\n" +
            "</div>\n" +
            "<div class=\"\">\n" +
            "  <label class=\"form-label\">Comment</label>\n" +
            "  <textarea class=\"form-control\" rows=\"3\" name='comment'>" + boardData.comment + "</textarea>\n" +
            "</div>" +
            "<div class=\"d-flex justify-content-end\">\n" +
            "  <button type='button' class=\"btn btn-warning\" id='boardUpdateBtn'>Update</button>\n" +
            "  <button type='button' class=\"btn btn-danger\" id='boardClosureBtn'>Closure</button>\n" +
            "</div>" +
            "</form>";
    } else {
        return "<form class='d-flex flex-column gap-3 p-3' id='boardForm'>" +
            "<div class=\"\">\n" +
            "  <label class=\"form-label\">Create user</label>\n" +
            "  <input type=\"text\" class=\"form-control\" name='createUser'>\n" +
            "</div>\n" +
            "<div class=\"\">\n" +
            "  <label class=\"form-label\">Title</label>\n" +
            "  <input type=\"text\" class=\"form-control\" name='title'>\n" +
            "</div>\n" +
            "<div class=\"\">\n" +
            "  <label class=\"form-label\">Comment</label>\n" +
            "  <textarea class=\"form-control\" rows=\"3\" name='comment'></textarea>\n" +
            "</div>" +
            "<div class=\"d-flex justify-content-end\">\n" +
            "  <button type='button' class=\"btn btn-info\" id='boardCreateBtn'>Create</button>\n" +
            "</div>" +
            "</form>";
    }
}

export {getBoardList, putBoardList, getBoard, getBoardFormHTML};