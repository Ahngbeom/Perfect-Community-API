/*
 * Copyright (C) 2023 Ahngbeom (bbu0704@gmail.com)
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

const FILTER_OPTIONS_KEY = "filter-options";

const postsByBoard = $("#postsByBoard");
const postsListUl = $("#postListByBoard");
const paginationNav = $("#postListByBoard").siblings("nav");
const paginationUl = paginationNav.find(".pagination");

function getPostList(option) {
    console.log(option);
    $.cookie(FILTER_OPTIONS_KEY, JSON.stringify(option));
    return $.ajax({
        type: 'get',
        url: '/api/post',
        contentType: 'application/json',
        dataType: 'json',
        data: option
    });
}

function putPostList(posts) {
    console.log(posts);
    postsListUl.html("");
    posts.forEach(post => {
        postsListUl.append("<li><button type='button' class='btn btn-link' data-pno='" + post.postNo + "'>" + post.title + "</button></li>")
    });

    const filterOptions = JSON.parse($.cookie(FILTER_OPTIONS_KEY));
    if (filterOptions.boardNo !== undefined) {
        getBoard(filterOptions.boardNo)
            .done((data) => {
                postsByBoard.find("label").text(data.title);
                // console.log(filterOptions.boardNo)
                if (filterOptions.boardNo > 0) {
                    $("#boardControl").removeClass("visually-hidden");
                }
            });
    } else {
        postsByBoard.find("label").text("전체 게시물");
        $("#boardControl").addClass("visually-hidden");
    }

    $.ajax({
        type: 'get',
        url: '/api/post/count',
        contentType: 'application/json',
        dataType: 'json',
        data: {boardNo: JSON.parse($.cookie(FILTER_OPTIONS_KEY)).boardNo},
        success: (result) => {
            /* Put posts count */
            $("#postCount").text("(총 게시물 수: " + result + ")");

            /* Set pagination */
            setPagination(1, Math.ceil(result / 10));
        }
    });
}

function setPagination(start, end) {
    paginationUl.html("<li class=\"page-item\"><button class=\"page-link\">Previous</button></li>");
    for (let i = start; i <= end; i++) {
        if (i === Number(JSON.parse($.cookie(FILTER_OPTIONS_KEY)).page))
            paginationUl.append("<li class=\"page-item active\"><button class=\"page-link\">" + i + "</button></li>");
        else
            paginationUl.append("<li class=\"page-item\"><button class=\"page-link\">" + i + "</button></li>");
    }
    paginationUl.append("<li class=\"page-item\"><button class=\"page-link\">Next</button></li>");
    paginationNav.removeClass("visually-hidden");
}

$(document).on('click', ".board-title", (e) => {
    const filter = {
        pageType: 'list',
        boardNo: $(e.target).data('bno'),
        page: 1
    }
    $.cookie(FILTER_OPTIONS_KEY, JSON.stringify(filter));
    getPostList(filter)
        .done((data) => putPostList(data));
});

$(document).on('click', '.page-link', (e) => {
    const activatedPage = Number($(".page-item.active").text());
    const firstPageNumber = Number($(document).find(paginationUl).children("li:nth-child(2)").text());
    const lastPageNumber = Number($(document).find(paginationUl).children("li:nth-last-child(2)").text());
    const boardNo = JSON.parse($.cookie(FILTER_OPTIONS_KEY)).boardNo;
    let targetPage = $(e.target).text();

    console.log({
        activatedPage: activatedPage,
        firstPageNumber: firstPageNumber,
        lastPageNumber: lastPageNumber,
        boardNo: boardNo,
        targetPage: targetPage
    });

    if (targetPage === 'Previous') {
        if (activatedPage === firstPageNumber)
            targetPage = firstPageNumber;
        else
            targetPage = activatedPage - 1;
    } else if (targetPage === 'Next') {
        if (activatedPage === lastPageNumber)
            targetPage = lastPageNumber;
        else
            targetPage = activatedPage + 1;
    }
    $(".page-item.active").removeClass("active");
    $(".page-item:nth-child(" + (Number(targetPage) + 1) + ")").addClass("active");
    getPostList({boardNo: boardNo, page: targetPage})
        .done((data) => {
            console.log(data);

            $("#postListByBoard").html("");
            data.forEach(post => {
                $("#postListByBoard").append("<li><button type='button' class='btn btn-link' data-pno='" + post.postNo + "'>" + post.title + "</button></li>")
            });
        });
});
