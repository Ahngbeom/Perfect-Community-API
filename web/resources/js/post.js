/*
 * Copyright (C) 23. 2. 3. 오후 1:15 Ahngbeom (https://github.com/Ahngbeom)
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

const postsByBoard = $("#postsByBoard");
const postsListUl = $("#postListByBoard");

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

    if (paginationData.pageAmount === undefined
        || paginationData.activatedPage === undefined
        || paginationData.startPage === undefined
        || paginationData.maximumPage === undefined) {
        $.ajax({
            type: 'get',
            url: '/api/post/count',
            async: false,
            contentType: 'application/json',
            dataType: 'json',
            data: {boardNo: postFilterOptions.boardNo}
        }).done((result) => {
            /* Put posts count */
            // $("#postCount").text("(총 게시물 수: " + result + ")");

            /* Set pagination */
            setCookie(PAGINATION_DATA_KEY, {
                pageAmount: Number(result),
                activatedPage: paginationData.activatedPage !== undefined ? paginationData.activatedPage : 1
            });
        });
    }
    $("#postCount").text("(총 게시물 수: " + paginationData.pageAmount + ")");
    initPagination();
}



$(document).on('click', ".board-title", (e) => {
    const filter = {
        postType: undefined,
        boardNo: $(e.target).data('bno')
    }
    setCookie(POST_FILTER_OPTIONS_KEY, filter);

    const paginationData = getCookieToJson(PAGINATION_DATA_KEY);
    console.log(paginationData);
    paginationData.activatedPage = 1;
    console.log(paginationData);
    setCookie(PAGINATION_DATA_KEY, paginationData);

    putPostList();
    // getPostAjax(filter)
    //     .done((data) => putPostList(data));
});


