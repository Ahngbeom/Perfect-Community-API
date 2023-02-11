import {initPagination} from "./pagination.js";
import {getBoard} from "../board/board.js";
import {getCookieToJson, setCookie} from "../pageCookie.js";
import {toLocaleDateTimeWithUpdateDate} from "../utils/date.js";
import {postList, postsByBoard} from "./post.js";

function getPosts() {
    let posts;
    const getPostsOptions = {
        boardNo: getCookieToJson(POST_FILTER_OPTIONS_COOKIE_NAME).boardNo,
        page: getCookieToJson(PAGINATION_DATA_COOKIE_NAME).activatedPage
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

function getPostAmount(option) {
    let result;
    $.ajax({
        type: 'get',
        url: '/api/post/count',
        async: false,
        data: option
    }).done((data) => {
        result = data;
    });
    return result;
}

function putPostList() {
    let postFilterOptions = getCookieToJson(POST_FILTER_OPTIONS_COOKIE_NAME);
    let paginationData = getCookieToJson(PAGINATION_DATA_COOKIE_NAME);

    if ($.isEmptyObject(paginationData)) {
        paginationData = {
            pageAmount: getPostAmount(),
            activatedPage: 1
        }
        setCookie(PAGINATION_DATA_COOKIE_NAME, paginationData);
    }
    const posts = getPosts();

    // console.log(postFilterOptions);
    // console.log(paginationData);
    // console.log(posts);
    // console.log(postsByBoard);
    // console.log(postList);

    $("#postCount").text("(총 게시물 수: " + paginationData.pageAmount + ")");

    postList.html("");
    posts.forEach(post => {
        postList.append("<li>" +
            "<button type='button' class='btn btn-link' data-pno='" + post.postNo + "'>" + post.title + "</button>" +
            "<span> " + toLocaleDateTimeWithUpdateDate(post.regDate, post.updateDate) + " </span>" +
            "</li>");
    });

    if (postFilterOptions.boardNo !== undefined) {
        const boardData = getBoard(postFilterOptions.boardNo);
        if (boardData === null) {
            setCookie(POST_FILTER_OPTIONS_COOKIE_NAME, {});
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

