import {getCookieToJson, setCookie} from "../pageCookie.js"
import {postScrapBtn} from "./scrap.js";

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
    // console.log(post);

    postFormRegDateElem.val(new Date(post.regDate).toLocaleString());
    postFormUpdateDateElem.val(new Date(post.updateDate).toLocaleString());
    postFormViewsElem.val(post.views);
    postFormRecommendElem.find("span").text(post.recommend);
    postFormNotRecommendElem.find("span").text(post.notRecommend);
    if (post.scraped)
        postScrapBtn.addClass('active');
    else
        postScrapBtn.removeClass('active');
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