/*
 * Copyright (C) 23. 2. 4. 오전 2:55 Ahngbeom (https://github.com/Ahngbeom)
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

import {PAGINATION_DATA_KEY, POST_FILTER_OPTIONS_KEY} from "../global_variable.js";
import {initPagination} from "./pagination.js";
import {getBoard} from "../board/board.js";
import {getCookieToJson} from "../pageCookie.js";

export const postsByBoard = $("#postsByBoard");
export const postsListUl = $("#postList");

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

    postsListUl.html("");
    posts.forEach(post => {
        postsListUl.append("<li><button type='button' class='btn btn-link' data-pno='" + post.postNo + "'>" + post.title + "</button></li>")
    });

    if (postFilterOptions.boardNo !== undefined) {
        getBoard(postFilterOptions.boardNo)
            .done((data) => {
                postsByBoard.find("label").text(data.title);
                // console.log(filterOptions.boardNo)
                if (postFilterOptions.boardNo > 0) {
                    $("#boardControl").removeClass("visually-hidden");
                }
            });
    } else {
        postsByBoard.find("label").text("전체 게시물");
        $("#boardControl").addClass("visually-hidden");
    }

    initPagination();
}

export {getPostAjax, putPostList};

