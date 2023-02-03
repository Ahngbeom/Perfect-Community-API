/*
 * Copyright (C) 23. 2. 3. 오후 6:38 Ahngbeom (https://github.com/Ahngbeom)
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

const POST_DETAILS_COOKIE_NAME = "post-details";
const postDetailsArea = $("#postDetails");
const postDetailsTitleElem = postDetailsArea.find("#postTitle");
const postDetailsContentsElem = postDetailsArea.find("#postContents");
const postDetailsCloseBtn = postDetailsArea.find(".btn-close");
let postDetailsCookieData = getCookieToJson(POST_DETAILS_COOKIE_NAME);

if (!$.isEmptyObject(postDetailsCookieData)) {
    getPostDetailsAjax(postDetailsCookieData.postNo).done((data) => putPostDetails(data));
}

function getPostDetailsAjax(postNo) {
    return $.ajax({
        type: 'get',
        url: '/api/post/' + postNo
    });
}

function putPostDetails(post) {
    postDetailsTitleElem.text(post.title);
    postDetailsContentsElem.text(post.contents);
    postDetailsArea.removeClass("visually-hidden");
    setCookie(POST_DETAILS_COOKIE_NAME, {postNo: post.postNo});
}

function clearPostDetails() {
    postDetailsTitleElem.text("");
    postDetailsContentsElem.text("");
    postDetailsArea.addClass("visually-hidden");
    setCookie(POST_DETAILS_COOKIE_NAME, {});
}

$(document).on('click', '#postList button', (e) => {
    const postNo = $(e.target).data('pno');
    getPostDetailsAjax(postNo).done((data) => putPostDetails(data));
});

postDetailsCloseBtn.on('click', clearPostDetails);