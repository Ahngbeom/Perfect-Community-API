import {clearPostDetails, getPost, putPostDetailsForm} from "./details.js"
import {putPostCreateForm} from "./create.js";
import {putPostUpdateForm} from "./update.js";
import {increaseViews} from "./interaction.js";
import "./scrap.js";

/* Elements related to the post */
export const postsByBoard = $("#postsByBoard");
export const postList = postsByBoard.find("#postList");
export const postForm = $("#postForm");
export const postDetailsAdditionalInfo = $("#postDetailsAdditionalInfo");
export const postFormRegDateElem = postForm.find("#postRegDate");
export const postFormUpdateDateElem = postForm.find("#postUpdateDate");
export const postFormViewsElem = postForm.find("#postViews");
export const postFormRecommendElem = postForm.find("#postRecommend");
export const postFormNotRecommendElem = postForm.find("#postNotRecommend");
export const postFormBoardTypeSelectElem = postForm.find("select[name='boardNo']");
export const postFormTypeSelectElem = postForm.find("select[name='postType']");
export const postFormTitleElem = postForm.find("#postTitle");
// const postFormLabelForTitleElem = postForm.find("label[for='" + postFormTitleElem.attr("id") + "']");
export const postFormContentsElem = postForm.find("#postContents");
// const postFormLabelForContentsElem = postForm.find("label[for='" + postFormContentsElem.attr("id") + "']");
export const postFormCloseBtn = postForm.find(".btn-close");
export const postUpdateBtn = $("#postUpdateBtn");
export const postScrapBtn = postForm.find("#postScrapBtn");

/* Events */
$("#showPostCreateFormBtn").on('click', putPostCreateForm);

$(document).on('click', "#showPostUpdateFormBtn", putPostUpdateForm);

$(document).on('click', '#postList button', (e) => {
    const postNo = $(e.target).data('pno');
    postUpdateBtn.off("click");
    postUpdateBtn.attr("id", "showPostUpdateFormBtn");
    postDetailsAdditionalInfo.removeClass("visually-hidden");
    increaseViews(postNo);
    putPostDetailsForm(getPost(postNo));
});

postFormCloseBtn.on('click', clearPostDetails);

postFormRecommendElem.on('click', () => {
    $.ajax({
        type: 'patch',
        url: '/api/post/recommend/' + postForm.data("post-no")
    }).done((data) => {
        postFormRecommendElem.find("span").text(data);
    });
});

postFormNotRecommendElem.on('click', () => {
    $.ajax({
        type: 'patch',
        url: '/api/post/not-recommend/' + postForm.data("post-no")
    }).done((data) => {
        postFormNotRecommendElem.find("span").text(data);
    });
});

postScrapBtn.on('click', () => {
    if (!postScrapBtn.hasClass('active')) {
        $.ajax({
            type: 'post',
            url: '/api/post/scrap/' + postForm.data('post-no')
        }).done(() => {
            postScrapBtn.addClass('active');
        }).fail((jqXHR) => {
            alert(jqXHR.responseText);
        });
    } else {
        $.ajax({
            type: 'delete',
            url: '/api/post/release-scrap/' + postForm.data('post-no')
        }).done(() => {
            postScrapBtn.removeClass('active');
        }).fail((jqXHR) => {
            alert(jqXHR.responseText);
        });
    }
});

