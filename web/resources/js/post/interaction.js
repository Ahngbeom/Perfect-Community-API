/*
 * Copyright (C) 23. 2. 7. 오후 11:51 Ahngbeom (https://github.com/Ahngbeom)
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

function increaseViews(postNo) {
    $.ajax({
        type: 'patch',
        url: '/api/post/views/' + postNo,
        async: false
    });
}

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

export {increaseViews}