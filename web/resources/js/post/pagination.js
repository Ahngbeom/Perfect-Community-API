/*
 * Copyright (C) 23. 2. 4. 오후 8:58 Ahngbeom (https://github.com/Ahngbeom)
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

import {getCookieToJson, setCookie} from "../pageCookie.js";
import {putPostList} from "./list.js";

const paginationNav = $("#paginationNav");
const paginationUl = paginationNav.find(".pagination");

function initPagination() {
    const paginationData = getCookieToJson(PAGINATION_DATA_COOKIE_NAME);
    paginationData.maximumPage = Math.ceil(paginationData.pageAmount / 10);
    paginationData.startPage = Math.floor(paginationData.activatedPage / 11) * 10 + 1;
    paginationData.endPage = (paginationData.maximumPage - paginationData.startPage) >= 10 ? paginationData.startPage + 9 : paginationData.maximumPage;

    if (paginationData.activatedPage === 1) {
        paginationUl.html("<li class=\"page-item\"><button class=\"page-link disabled\">Previous</button></li>");
    } else {
        paginationUl.html("<li class=\"page-item\"><button class=\"page-link\">Previous</button></li>");
    }
    for (let i = paginationData.startPage; i <= paginationData.maximumPage && i <= paginationData.startPage + 9; i++) {
        if (i === paginationData.activatedPage)
            paginationUl.append("<li class=\"page-item active\"><button class=\"page-link\">" + i + "</button></li>");
        else
            paginationUl.append("<li class=\"page-item\"><button class=\"page-link\">" + i + "</button></li>");
    }
    if (paginationData.activatedPage === paginationData.maximumPage || paginationData.maximumPage === 0) {
        paginationUl.append("<li class=\"page-item\"><button class=\"page-link disabled\">Next</button></li>");
    } else {
        paginationUl.append("<li class=\"page-item\"><button class=\"page-link\">Next</button></li>");
    }
    paginationNav.removeClass("visually-hidden");
    setCookie(PAGINATION_DATA_COOKIE_NAME, paginationData);
}

/* Activation page replacement */
function activePagination(selectedPageElem) {
    $(".page-item.active").removeClass("active");
    if (selectedPageElem.text() !== 'Previous' && selectedPageElem.text() !== 'Next') {
        selectedPageElem.parent(".page-item").addClass('active');
    }
}

/* Set cookie by selected page */
$(document).on('click', '.page-link', (e) => {
    const beforePageItem = $(".page-item.active");
    const beforePageNumber = Number(beforePageItem.text());
    const selectedPageItemText = $(e.target).text();
    const paginationData = getCookieToJson(PAGINATION_DATA_COOKIE_NAME);

    if (selectedPageItemText === 'Previous') {
        if (beforePageNumber > 1) {
            paginationData.activatedPage = paginationData.activatedPage - 1;
        }
    } else if (selectedPageItemText === 'Next') {
        if (beforePageNumber < paginationData.maximumPage) {
            paginationData.activatedPage = paginationData.activatedPage + 1;
        }
    } else {
        paginationData.activatedPage = Number(selectedPageItemText);
    }
    setCookie(PAGINATION_DATA_COOKIE_NAME, paginationData);
    putPostList();
});

export {initPagination};
