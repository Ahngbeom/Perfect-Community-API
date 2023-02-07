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

import {getPost, putPostDetailsForm} from "./details.js"
import {putPostCreateForm} from "./create.js";
import {putPostUpdateForm} from "./update.js";
import {increaseViews} from "./interaction.js";

const postUpdateBtn = $("#postUpdateBtn");

$("#showPostCreateFormBtn").on('click', putPostCreateForm);

$(document).on('click', "#showPostUpdateFormBtn", putPostUpdateForm);

$(document).on('click', '#postList button', (e) => {
    const postNo = $(e.target).data('pno');
    postUpdateBtn.off("click");
    postUpdateBtn.attr("id", "showPostUpdateFormBtn");
    increaseViews(postNo);
    putPostDetailsForm(getPost(postNo));
});