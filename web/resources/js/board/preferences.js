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

import {getBoard, getBoardFormHTML} from "./board.js";
import {getCookieToJson} from "../pageCookie.js";

$(document).on('click', "#boardPreferences", (e) => {
    const selectedBoardNo = getCookieToJson(POST_FILTER_OPTIONS_KEY);
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
    const updateBoardNo = boardForm.data("bno");

    $.ajax({
        type: 'delete',
        url: '/api/board/' + updateBoardNo
    }).done(() => {
        location.reload();
    }).fail((jqXHR) => {
        alert(jqXHR.responseText);
    });
});

