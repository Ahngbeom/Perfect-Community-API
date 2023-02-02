/*
 * Copyright (C) 23. 2. 3. 오전 12:17 Ahngbeom (https://github.com/Ahngbeom)
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

// let activePage = $(".page-item.active");

function initPagination() {
    const paginationData = getCookieToJson(PAGINATION_DATA_KEY);
    paginationData.maximumPage = Math.ceil(paginationData.pageAmount / 10);
    paginationData.startPage = Math.floor(paginationData.activatedPage / 10) * 10 + 1;
    paginationUl.html("<li class=\"page-item\"><button class=\"page-link\">Previous</button></li>");
    for (let i = paginationData.startPage; i <= paginationData.maximumPage && i <= paginationData.startPage + 9; i++) {
        if (i === paginationData.activatedPage)
            paginationUl.append("<li class=\"page-item active\"><button class=\"page-link\">" + i + "</button></li>");
        else
            paginationUl.append("<li class=\"page-item\"><button class=\"page-link\">" + i + "</button></li>");
    }
    setCookie(PAGINATION_DATA_KEY, paginationData);
    paginationUl.append("<li class=\"page-item\"><button class=\"page-link\">Next</button></li>");
    paginationNav.removeClass("visually-hidden");
}

$(document).on('click', '.page-link', () => {
    // console.log(Number(activePage.text()));
    // activePage = $(".page-item.active");
});

$(document).on('click', '.page-link', (e) => {
    const beforePageItem = $(".page-item.active");
    const beforePageNumber = Number(beforePageItem.text());
    // const firstPageNumber = Number($(document).find(paginationUl).children("li:nth-child(2)").text());
    // const lastPageNumber = Number($(document).find(paginationUl).children("li:nth-last-child(2)").text());
    const boardNo = getCookieToJson(POST_FILTER_OPTIONS_KEY).boardNo;
    const selectedPageItemText = $(e.target).text();
    const paginationData = getCookieToJson(PAGINATION_DATA_KEY);

    // console.log({
    //     activatedPage: beforePageNumber,
    //     firstPageNumber: firstPageNumber,
    //     lastPageNumber: lastPageNumber,
    //     boardNo: boardNo,
    //     targetPage: targetPage
    // });

    if (selectedPageItemText === 'Previous') {
        if (beforePageNumber > 1)
            paginationData.activatedPage = paginationData.activatedPage - 1;
    } else if (selectedPageItemText === 'Next') {
        if (beforePageNumber < paginationData.maximumPage) {
            paginationData.activatedPage = paginationData.activatedPage + 1;
            if (beforePageNumber % 10 === 0) {
                initPagination();
            }
        }
    } else {
        paginationData.activatedPage = Number(selectedPageItemText);
    }
    setCookie(PAGINATION_DATA_KEY, paginationData);

    beforePageItem.removeClass("active");
    $(".page-item:nth-child(" + (paginationData.activatedPage + 1) + ")").addClass("active");
    getPostAjax({boardNo: boardNo, page: paginationData.activatedPage})
        .done((data) => {
            // console.log(data);

            $("#postListByBoard").html("");
            data.forEach(post => {
                $("#postListByBoard").append("<li><button type='button' class='btn btn-link' data-pno='" + post.postNo + "'>" + post.title + "</button></li>")
            });
        });
});

/*
* 1. Set cookie for pagination
* 2. Put pagination to HTML
* 3. Get and put posts by activated page
*
*
*
*
* */