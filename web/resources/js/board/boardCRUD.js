const postsForm = document.querySelector("#postsForm");
const postsDeleteBtn = postsForm !== null ? postsForm.querySelector("#postsDeleteBtn") : undefined;
const postsModifyBtn = postsForm !== null ? postsForm.querySelector("#postsModifyBtn") : undefined;
const postsWriterInput = postsForm !== null ? postsForm.querySelector("input[name='writer']") : undefined;
const postsTitleInput = postsForm !== null ? postsForm.querySelector("input[name='title']") : undefined;
const postsContentInput = postsForm !== null ? postsForm.querySelector("textarea[name='content']") : undefined;

if (postsDeleteBtn) {
    postsDeleteBtn.addEventListener('click', () => {
        $.ajax({
            type: 'GET',
            url: '/rest/board/hasPassword',
            contentType: 'application/json; charset=utf-8',
            data: {bno: postsForm.querySelector("input[name='bno']").value},
            success: function (data) {
                if (data === "isAdmin")
                    ajaxPostDelete({bno: document.querySelector("#postsForm").querySelector("input[name='bno']").value});
                else if (data === true) {
                    switchToPasswordInputModal();
                    centerModalSubmit.addEventListener('click', function () {
                        ajaxPostDelete({
                            bno: document.querySelector("#postsForm").querySelector("input[name='bno']").value,
                            boardPassword: centerModalElem.querySelector(".modal-body input[name='password']").value
                        });
                    }, {once: true});
                } else
                    putServerAlert("게시물 삭제 권한이 없습니다.");
            },
            error: function (data) {
                console.error(data);
                putServerAlert("게시물 삭제 권한이 없습니다.");
            }
        });
    });
}

if (postsModifyBtn) {
    postsModifyBtn.addEventListener('click', () => {
        $.ajax({
            type: 'GET',
            url: '/rest/board/hasPassword',
            contentType: 'application/json; charset=utf-8',
            data: {bno: postsForm.querySelector("input[name='bno']").value},
            success: function (data) {
                if (data === "isAdmin")
                    ajaxPostModify({bno: document.querySelector("#postsForm").querySelector("input[name='bno']").value});
                if (data === true) {
                    switchToPasswordInputModal();
                    centerModalSubmit.addEventListener('click', function () {
                        if (boardPasswordMatches({
                            bno: document.querySelector("#postsForm").querySelector("input[name='bno']").value,
                            boardPassword: centerModalElem.querySelector(".modal-body input[name='password']").value
                        })) {
                            releasePostInput();
                            postsContentInput.parentElement.insertAdjacentHTML("afterend", "<div class=\"form-group\">\n" +
                                "                <label for=\"boardRegisterFormTitle\">비밀번호</label>\n" +
                                "                <div class=\"w-100\">\n" +
                                "                    <input type=\"password\" name=\"boardPassword\" class=\"form-control\"\n" +
                                "                           id=\"modifyPostPassword\"/>\n" +
                                "                    <p></p>\n" +
                                "                </div>\n" +
                                "            </div>")
                            centerModal.hide();
                            postsModifyBtn.addEventListener('click', () => {
                                ajaxPostModify({
                                    bno: postsForm.querySelector("input[name='bno']").value,
                                    writer: postsWriterInput.value,
                                    title: postsTitleInput.value,
                                    content: postsContentInput.value,
                                    boardPassword: postsForm.querySelector("input[name='boardPassword']").value
                                });
                            }, {once: true});
                        } else {
                            postsWriterInput.setAttribute("readonly", "true");
                            postsTitleInput.setAttribute("readonly", "true");
                            postsContentInput.setAttribute("readonly", "true");
                            switchRetryModal(releasePostInput);
                        }
                    }, {once: true});
                } else
                    putServerAlert("게시물 수정 권한이 없습니다.");
            },
            error: function (data) {
                console.error(data);
                putServerAlert("게시물 수정 권한이 없습니다.");
            }
        });
    }, {once: true});
}

const existBoardPasswordCheck = function (bno) {
    let result = false;
    $.ajax({
        url: "/rest/board/passwordCheck",
        type: "POST",
        async: false,
        data: {bno: bno},
        success: function (data) {
            result = data;
        }
    });
    return result;
}

const boardPasswordMatches = function (data) {
    let result = false;
    $.ajax({
        type: "POST",
        url: "/rest/board/passwordMatches",
        async: false,
        contentType: 'application/json; charset=utf-8',
        dataType: 'JSON',
        data: JSON.stringify(data),
        success: function (data) {
            result = data;
        },
        error: function (data) {
            console.error(data);
        }
    });
    return result;
}

const ajaxPostDelete = function (data) {
    $.ajax({
        type: 'POST',
        url: '/rest/board/remove',
        contentType: 'application/json; charset=utf-8',
        dataType: 'JSON',
        data: JSON.stringify(data),
        success: function (data, status) {
            console.log(data);
            console.log(status);
            centerModalElem.querySelector('.modal-title').innerHTML = "Alert";
            centerModalElem.querySelector('.modal-body').innerHTML = "게시물이 삭제되었습니다.";
            centerModalSubmit.innerHTML = "확인";
            centerModal.show();
            centerModalSubmit.addEventListener('click', function () {
                location.assign("/board/list");
            }, {once: true});
        },
        error: function (data) {
            if (data.responseText === 'Incorrect Password') {
                switchRetryModal();
            } else
                putServerAlert("게시물 삭제 권한이 없습니다.");
        }
    });
}

const ajaxPostModify = function (data) {
    console.log("ajaxPostModify", data);
    $.ajax({
        type: 'POST',
        url: '/rest/board/modify',
        async: false,
        contentType: 'application/json; charset=utf-8',
        dataType: 'JSON',
        data: JSON.stringify(data),
        success: function (data, status) {
            console.log(data);
            console.log(status);
            centerModalElem.querySelector('.modal-title').innerHTML = "Alert";
            centerModalElem.querySelector('.modal-body').innerHTML = "게시물이 수정되었습니다.";
            centerModalSubmit.innerHTML = "확인";
            centerModal.show();
            centerModalSubmit.addEventListener('click', function () {
                location.reload();
            }, {once: true});
        },
        error: function (data) {
            console.error(data);
        }
    });
}

const releasePostInput = function () {
    postsWriterInput.removeAttribute("readonly");
    postsTitleInput.removeAttribute("readonly");
    postsContentInput.removeAttribute("readonly");
}

