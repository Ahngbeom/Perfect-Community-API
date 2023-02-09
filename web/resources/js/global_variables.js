/*
 * Copyright (C) 23. 2. 7. 오후 11:57 Ahngbeom (https://github.com/Ahngbeom)
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

/**
 *
 * username is authenticated user ID
 *
 * userRole is authenticated user authorities
 *
 * accessToken is JWT issued for access by authentication
 *
 **/
let userData = {};

/* Constant Cookie names */
const POST_FILTER_OPTIONS_COOKIE_NAME = "post-filter-options";
const PAGINATION_DATA_COOKIE_NAME = "pagination";
const POST_DETAILS_COOKIE_NAME = "post-details";

/* Additional buttons in page */
const additionalArea = $("#additionalArea");
const additionalButtonsArea = $("#additionalButtons");

/* Main contents in page */
const mainContents = $("#mainContents");

/* Elements related to the post */
const postsByBoard = $("#postsByBoard");
const postList = $("#postList");

const postForm = $("#postForm");
const postDetailsAdditionalInfo = $("#postDetailsAdditionalInfo")
const postFormRegDateElem = postForm.find("#postRegDate");
const postFormUpdateDateElem = postForm.find("#postUpdateDate");
const postFormViewsElem = postForm.find("#postViews");
const postFormRecommendElem = postForm.find("#postRecommend");
const postFormNotRecommendElem = postForm.find("#postNotRecommend");
const postFormBoardTypeSelectElem = postForm.find("select[name='boardNo']");
const postFormTypeSelectElem = postForm.find("select[name='postType']");
const postFormTitleElem = postForm.find("#postTitle");
// const postFormLabelForTitleElem = postForm.find("label[for='" + postFormTitleElem.attr("id") + "']");
const postFormContentsElem = postForm.find("#postContents");
// const postFormLabelForContentsElem = postForm.find("label[for='" + postFormContentsElem.attr("id") + "']");

const postFormCloseBtn = postForm.find(".btn-close");