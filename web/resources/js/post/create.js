/*
 * Copyright (C) 23. 2. 6. 오전 3:30 Ahngbeom (https://github.com/Ahngbeom)
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

function putPostCreateForm() {
    postFormBoardTypeSelectElem.attr("disabled", false);
    postFormTypeSelectElem.attr("disabled", false);
    $(postForm).find("label[for='" + postFormTitleElem.attr("id") + "']").text("Title");
    postFormTitleElem.removeClass("form-control-plaintext").addClass("form-control")
        .attr("readonly", false)
        .val("");
    $(postForm).find("label[for='" + postFormContentsElem.attr("id") + "']").text("Contents");
    postFormContentsElem.removeClass("form-control-plaintext").addClass("form-control")
        .attr("readonly", false)
        .text("");

    $("#postCreateBtn").removeClass("visually-hidden");
    $("#showPostUpdateFormBtn").addClass("visually-hidden");
    $("#postRemoveBtn").addClass("visually-hidden");

    postForm.removeClass("visually-hidden");
}

$("#postCreateBtn").on('click', () => {
    const createPostData = {
        boardNo: postFormBoardTypeSelectElem.val(),
        type: postFormTypeSelectElem.val(),
        title: postFormTitleElem.val(),
        contents: postFormContentsElem.val()
    }
    console.log(createPostData);
    $.ajax({
        type: 'post',
        url: '/api/post',
        data: JSON.stringify(createPostData)
    }).done((data) => {
        let postDetailsCookieData = getCookieToJson(POST_DETAILS_COOKIE_NAME);
        postDetailsCookieData.postNo = data;
        setCookie(POST_DETAILS_COOKIE_NAME, postDetailsCookieData)
        location.reload();
    }).fail((jqXHR) => {
        alert(jqXHR.responseText);
    });
});

export {putPostCreateForm}