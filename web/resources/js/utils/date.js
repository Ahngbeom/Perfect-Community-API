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

function toLocaleDateTimeWithUpdateDate(regDateMillis, updateDateMillis) {
    let updateDateDiff = (Date.now() - updateDateMillis);

    // console.log({
    //     seconds: updateDateDiff / 1000,
    //     minute: updateDateDiff / 1000 / 60,
    //     hour: updateDateDiff / 1000 / 60 / 60,
    //     days: updateDateDiff / 1000 / 60 / 60 / 24,
    // })

    if (regDateMillis < updateDateMillis) {
        if (updateDateDiff / 1000 < 60) {
            updateDateDiff = Math.floor(updateDateDiff / 1000) + "초 전 수정됨";
        } else if (updateDateDiff / 1000 / 60 < 60) {
            updateDateDiff = Math.floor((updateDateDiff / 1000) / 60) + "분 전 수정됨";
        } else if (updateDateDiff / 1000 / 60 / 60 < 24) {
            updateDateDiff = Math.floor(updateDateDiff / 1000 / 60 / 60) + "시간 전 수정됨";
        } else {
            updateDateDiff = Math.floor(updateDateDiff / 1000 / 60 / 60 / 24) + "일 전 수정됨";
        }
        return new Date(regDateMillis).toLocaleString() + " " + updateDateDiff;
    } else {
        return new Date(regDateMillis).toLocaleString();
    }

}

export {toLocaleDateTimeWithUpdateDate}