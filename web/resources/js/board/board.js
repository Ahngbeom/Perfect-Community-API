/*
 * Copyright (C) 23. 2. 5. 오전 4:24 Ahngbeom (https://github.com/Ahngbeom)
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

import {putPostList} from "../post/list.js";
import {setCookie} from "../pageCookie.js";

getBoardList();

function getBoardList() {
    return $.ajax({
        type: 'get',
        url: '/api/board',
        success: (data) => {
            // console.log(data);
            data.forEach(board => {
                $("#boardList ul").append("<li><button type='button' class='btn btn-link board-title' data-bno='" + board.bno + "'>" + board.title + "</button></li>")
            });
        }
    });
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

        setCookie(POST_FILTER_OPTIONS_KEY, postFilterOptions);

        /* Set pagination */
        setCookie(PAGINATION_DATA_KEY, {
            pageAmount: Number(result),
            activatedPage: 1
        });
    });

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

export {getBoard, getBoardFormHTML};