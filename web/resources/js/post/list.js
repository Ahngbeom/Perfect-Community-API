/*
 * Copyright (C) 23. 2. 7. 오후 11:25 Ahngbeom (https://github.com/Ahngbeom)
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

import {initPagination} from "./pagination.js";
import {getBoard} from "../board/board.js";
import {getCookieToJson, setCookie} from "../pageCookie.js";
import {toLocaleDateTimeWithUpdateDate} from "../utils/date.js";

putPostList();

function getPosts() {
    let posts;
    const getPostsOptions = {
        boardNo: getCookieToJson(POST_FILTER_OPTIONS_KEY).boardNo,
        page: getCookieToJson(PAGINATION_DATA_KEY).activatedPage
    }
    $.ajax({
        type: 'get',
        url: '/api/post',
        async: false,
        contentType: 'application/json',
        dataType: 'json',
        data: getPostsOptions
    }).done((data) => {
        posts = data;
    });
    return posts;
}

function getPostAjax(option) {
    return $.ajax({
        type: 'get',
        url: '/api/post',
        contentType: 'application/json',
        dataType: 'json',
        data: option
    });
}

function putPostList() {
    let postFilterOptions = getCookieToJson(POST_FILTER_OPTIONS_KEY);
    let paginationData = getCookieToJson(PAGINATION_DATA_KEY);
    const posts = getPosts();

    $("#postCount").text("(총 게시물 수: " + paginationData.pageAmount + ")");

    unorderedListForPosts.html("");
    posts.forEach(post => {

        unorderedListForPosts.append("<li>" +
            "<button type='button' class='btn btn-link' data-pno='" + post.postNo + "'>" + post.title + "</button>" +
            "<span> " + toLocaleDateTimeWithUpdateDate(post.regDate, post.updateDate) + " </span>" +
            "</li>")
    });

    if (postFilterOptions.boardNo !== undefined) {
        const boardData = getBoard(postFilterOptions.boardNo);
        if (boardData === null) {
            setCookie(POST_FILTER_OPTIONS_KEY, {});
            return;
        }
        postsByBoard.find("label").text(boardData.title);
        // console.log(filterOptions.boardNo)
        if (postFilterOptions.boardNo > 0) {
            $("#boardControl").removeClass("visually-hidden");
        }
    } else {
        postsByBoard.find("label").text("전체 게시물");
        $("#boardControl").addClass("visually-hidden");
    }

    initPagination();
}

export {putPostList};

