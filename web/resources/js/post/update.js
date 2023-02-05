/*
 * Copyright (C) 23. 2. 5. 오후 11:36 Ahngbeom (https://github.com/Ahngbeom)
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

function putPostUpdateForm() {
    postFormBoardTypeSelectElem.attr("disabled", false);
    postFormTypeSelectElem.attr("disabled", false);
    $(postForm).find("label[for='" + postFormTitleElem.attr("id") + "']").text("Title");
    postFormTitleElem.removeClass("form-control-plaintext").addClass("form-control")
        .attr("readonly", false);
    $(postForm).find("label[for='" + postFormContentsElem.attr("id") + "']").text("Contents");
    postFormContentsElem.removeClass("form-control-plaintext").addClass("form-control")
        .attr("readonly", false);

    $("#postCreateBtn").addClass("visually-hidden");
    $("#showPostUpdateFormBtn").removeClass("visually-hidden");
    $("#showPostUpdateFormBtn").attr("id", "postUpdateBtn");
    $("#postRemoveBtn").removeClass("visually-hidden");

    $("#postUpdateBtn").one('click', () => {
        const updatePostData = {
            boardNo: postFormBoardTypeSelectElem.val(),
            type: postFormTypeSelectElem.val(),
            title: postFormTitleElem.val(),
            contents: postFormContentsElem.val()
        }
        $.ajax({
            type: 'patch',
            url: '/api/post/' + postForm.data('post-no'),
            data: JSON.stringify(updatePostData)
        }).done((data) => {
            location.reload();
        }).fail((jqXHR) => {
            alert(jqXHR.responseText);
        });
    });

    postForm.removeClass("visually-hidden");
}

export {putPostUpdateForm}