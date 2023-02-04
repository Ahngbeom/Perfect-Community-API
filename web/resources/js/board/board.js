/*
 * Copyright (C) 23. 2. 5. 오전 12:07 Ahngbeom (https://github.com/Ahngbeom)
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
import {getCookieToJson, setCookie} from "../pageCookie.js";

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

$("#boardInfoBtn").on('click', () => {
    getBoard(getCookieToJson(POST_FILTER_OPTIONS_KEY).boardNo)
        .done((data) => {
            console.log(data);
            // setCookie(POST_FILTER_OPTIONS_KEY, {
            //     boardNo: data.bno,
            //     pageType: 'read'
            // });
            // postsByBoard.html(putBoardFormHTML(data));
        });
});

const getBoardFormHTML = ((boardData) => {
    return "<form class='p-3' id='boardForm' data-bno='" + boardData.bno + "'>" +
        "<div class=\"mb-3\">\n" +
        "  <label class=\"form-label\">Bulletin board created user</label>\n" +
        "  <input type=\"text\" class=\"form-control\" name='createUser' value='" + boardData.createUser + "'>\n" +
        "</div>\n" +
        "<div class=\"mb-3\">\n" +
        "  <label class=\"form-label\">Title</label>\n" +
        "  <input type=\"text\" class=\"form-control\" name='title' value='" + boardData.title + "'>\n" +
        "</div>\n" +
        "<div class=\"mb-3\">\n" +
        "  <label class=\"form-label\">Comment</label>\n" +
        "  <textarea class=\"form-control\" rows=\"3\" name='comment'>" + boardData.comment + "</textarea>\n" +
        "</div>" +
        "<div class=\"mb-3\">\n" +
        "  <button type='button' class=\"btn btn-warning\" id='boardUpdateBtn'>Update</button>\n" +
        "  <button type='button' class=\"btn btn-danger\" id='boardClosureBtn'>Closure</button>\n" +
        "</div>" +
        "</form>";
});

export {getBoard, getBoardFormHTML};