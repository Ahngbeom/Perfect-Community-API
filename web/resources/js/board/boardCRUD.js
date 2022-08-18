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
                    if (data) {
                        $("#centerModal").find("#centerModalTitle").text("비밀번호 확인");
                        $("#centerModal").find(".modal-body").html("<input type='password' name='password' placeholder='Password' class='form-control'/><p></p>");
                        $("#centerModal").find("#centerModalSubmit").text("확인");
                        $("#centerModal").modal();

                        $("#centerModal").find("#centerModalSubmit")[0].addEventListener('click', () => {
                            ajaxPostDelete({
                                bno: postsForm.querySelector("input[name='bno']").value,
                                boardPassword: document.querySelector(".modal-body input[name='password']").value
                            });

                            // if (boardPasswordMatches(postsForm.querySelector("input[name='bno']").value, document.querySelector(".modal-body input[name='password']").value)) {
                            //     postsForm.setAttribute("method", "POST");
                            //     postsForm.setAttribute("action", "/board/remove");
                            //     postsForm.submit();
                            // } else {
                            //     putMessageForInputTag("danger", $("#centerModal").find(".modal-body input[name='password']")[0], "비밀번호가 일치하지않습니다.");
                            // }
                        });

                    }
                },
                error: function (data) {
                    console.error(data);
                }
            });

            // if (loggedInStatus()) {
            //     postsForm.setAttribute("method", "POST");
            //     postsForm.setAttribute("action", "/board/remove");
            //     postsForm.submit();
            // } else if (existBoardPasswordCheck(postsForm.querySelector("input[name='bno']").value)) {
            //     $("#centerModal").find("#centerModalTitle").text("비밀번호 확인");
            //     $("#centerModal").find(".modal-body").html("<input type='password' name='password' placeholder='Password' class='form-control'/><p></p>");
            //     $("#centerModal").find("#centerModalSubmit").text("확인");
            //     $("#centerModal").modal();
            //
            //     $("#centerModal").find("#centerModalSubmit")[0].addEventListener('click', () => {
            //
            //
            //         if (boardPasswordMatches(postsForm.querySelector("input[name='bno']").value, document.querySelector(".modal-body input[name='password']").value)) {
            //             postsForm.setAttribute("method", "POST");
            //             postsForm.setAttribute("action", "/board/remove");
            //             postsForm.submit();
            //         } else {
            //             putMessageForInputTag("danger", $("#centerModal").find(".modal-body input[name='password']")[0], "비밀번호가 일치하지않습니다.");
            //         }
            //     });
            // } else {
            //     putServerAlert("로그인 후 진행해주세요.");
            // }
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
            },
            error: function (data) {
                console.error(data.responseText);
                putServerAlert("게시물 삭제 권한이 없습니다.");
            }
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

