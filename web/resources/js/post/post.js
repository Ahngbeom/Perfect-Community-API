import {getPost, putPostDetailsForm} from "./details.js"
import {putPostCreateForm} from "./create.js";
import {putPostUpdateForm} from "./update.js";
import {increaseViews} from "./interaction.js";
import "./scrap.js";

const postUpdateBtn = $("#postUpdateBtn");

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