import {
    postDetailsAdditionalInfo,
    postForm,
    postFormBoardTypeSelectElem,
    postFormContentsElem,
    postFormTitleElem,
    postFormTypeSelectElem
} from "./post.js";

function putPostUpdateForm() {
    postDetailsAdditionalInfo.addClass("visually-hidden");
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