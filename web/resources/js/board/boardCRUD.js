document.addEventListener('DOMContentLoaded', () => {
    const postsForm = document.querySelector("#postsForm");
    const postsDeleteBtn = postsForm !== null ? postsForm.querySelector("#postsDeleteBtn") : undefined;
    const postsModifyBtn = postsForm !== null ? postsForm.querySelector("#postsModifyBtn") : undefined;
    const postsWriterInput = postsForm !== null ? postsForm.querySelector("input[name='writer']") : undefined;
    const postsTitleInput = postsForm !== null ? postsForm.querySelector("input[name='title']") : undefined;
    const postsContentInput = postsForm !== null ? postsForm.querySelector("textarea[name='content']") : undefined;

    if (postsDeleteBtn !== undefined) {
        postsDeleteBtn.addEventListener('click', () => {
            $.ajax({
                type: 'GET',
                url: '/rest/board/hasPassword',
                contentType: 'application/json; charset=utf-8',
                data: {bno: postsForm.querySelector("input[name='bno']").value},
                success: function (data) {
                    if (data === "isAdmin")
                        ajaxPostDelete({bno: document.querySelector("#postsForm").querySelector("input[name='bno']").value});
                    if (data === true) {
                        switchToPasswordInputModal();
                    }
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
            // if (${not empty isAdmin}) {
            //     postsWriterInput.removeAttr('readonly');
            //     postsTitleInput.removeAttr('readonly');
            //     postsContentInput.removeAttr('readonly');
            // } else {
            //     putServerAlert("게시물 수정 권한이 없습니다.");
            // }
        });
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

    const boardPasswordMatches = function (bno, password) {
        let result = false;
        $.ajax({
            url: "/rest/board/passwordMatches",
            type: "POST",
            async: false,
            data: {
                bno: bno,
                password: password
            },
            success: function (data) {
                result = data;
            }
        });
        return result;
    }
});

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
                centerModalElem.querySelector('.modal-title').innerHTML = "Alert";
                centerModalElem.querySelector('.modal-body').innerHTML = "비밀번호가 일치하지않습니다.";
                centerModalElem.querySelector('#centerModalSubmit').classList.remove('btn-info');
                centerModalElem.querySelector('#centerModalSubmit').classList.add('btn-danger');
                centerModalElem.querySelector('#centerModalSubmit').innerHTML = "다시시도";
                centerModalSubmit.addEventListener('click', function () {
                    switchToPasswordInputModal();
                }, {once: true});
            } else
                putServerAlert("게시물 삭제 권한이 없습니다.");
        }
    });
}

