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

import {getCookieToJson, setCookie} from "../pageCookie.js"
// import {boardDataList} from "../board/board.js";

let postDetailsCookieData = getCookieToJson(POST_DETAILS_COOKIE_NAME);

if (!$.isEmptyObject(postDetailsCookieData)) {
    putPostDetailsForm(getPost(postDetailsCookieData.postNo));
}

function getPost(postNo) {
    if (postNo === undefined)
        return null;
    let result = null;
    $.ajax({
        type: 'get',
        url: '/api/post/' + postNo,
        async: false
    }).done((data) => {
        result = data;
    });
    return result;
}

function putPostDetailsForm(post) {

    console.log(post);

    postFormRegDateElem.val(new Date(post.regDate).toLocaleString());
    postFormUpdateDateElem.val(new Date(post.updateDate).toLocaleString());
    postFormViewsElem.val(post.views);
    postFormRecommendElem.val(post.recommend);
    postFormNotRecommendElem.val(post.notRecommend);

    for (const optionElem of postFormBoardTypeSelectElem.find("option")) {
        if (post.boardNo === $(optionElem).val()) {
            $(optionElem).attr("selected", true);
        }
    }
    for (const option of postFormTypeSelectElem.find("option")) {
        if (post.type === $(option).val()) {
            $(option).attr("selected", true);
        }
    }
    postForm.data("post-no", post.postNo);
    // postFormLabelForTitleElem.text("");
    postFormTitleElem.removeClass("form-control").addClass("form-control-plaintext")
        .val(post.title)
        .attr("readonly", true);
    // postFormLabelForContentsElem.text("");

    postFormContentsElem.removeClass("form-control").addClass("form-control-plaintext")
        .text(post.contents)
        .attr("readonly", true);

    postForm.removeClass("visually-hidden");

    if (userData.username === post.writer) {
        $("#postCreateBtn").addClass("visually-hidden");
        $("#showPostUpdateFormBtn").removeClass("visually-hidden");
        $("#postRemoveBtn").removeClass("visually-hidden");
    }

    setCookie(POST_DETAILS_COOKIE_NAME, {postNo: post.postNo});
}

function clearPostDetails() {
    postFormTitleElem.text("");
    // postFormLabelForTitleElem.text("");
    postFormContentsElem.text("");
    // postFormLabelForContentsElem.text("");
    postForm.addClass("visually-hidden");
    setCookie(POST_DETAILS_COOKIE_NAME, {});
}

postFormCloseBtn.on('click', clearPostDetails);

export {getPost, putPostDetailsForm, clearPostDetails}