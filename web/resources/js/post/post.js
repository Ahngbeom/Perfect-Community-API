$("#api-post-list-btn").on('click', () => {
    $.ajax({
        type: 'get',
        url: '/api/post',
        contentType: 'application/json'
    })
});

$("#api-post-list-with-options-btn").on('click', (e) => {
    const liElem = $(e.target).parents("li");
    const keyword = liElem.find("input[name='keyword']").val();
    const boardNo = liElem.find("input[name='boardNo']").val();
    const page = liElem.find("input[name='page']").val();
    const type = liElem.find("select[name='type']").val();

    $.ajax({
        type: 'get',
        url: '/api/post',
        contentType: 'application/json',
        dataType: 'json',
        data: {
            keyword: keyword !== '' ? keyword : undefined,
            boardNo: boardNo !== '' ? boardNo : 0,
            page: page !== '' ? page : 0,
            type: type !== 'null' ? type : undefined
        }
    })
});

$("#api-post-info-btn").on('click', (e) => {
    $.ajax({
        type: 'get',
        url: '/api/post/' + $(e.target).siblings("label").children("input[type='number']").val(),
        contentType: 'application/json'
    })
});

$(".api-post-create-btn").on('click', (e) => {
    function ajaxPostRegistration() {
        const liElem = $(e.target).parents("li");
        $.ajax({
            type: 'post',
            url: '/api/post',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                title: liElem.find("input[name='title']").val(),
                comment: liElem.find("textarea[name='comment']").val()
            })
        });
    }

    if (userID === '') {
        loginSubmitBtn.on('click', () => {
            console.log(
                {
                    userId: loginModalElem.find("input[name='username']").val(),
                    userPw: loginModalElem.find("input[name='password']").val()
                }
            )
            $.when(ajaxLogin())
                .done(() => {
                    loginModal.hide();
                    // userState.load(window.location.href + ' #user-state');
                    ajaxPostRegistration();
                })
                .fail((response) => {
                    console.error(response);
                });
        });
        loginModal.show();
    } else {
        ajaxPostRegistration();
    }
});

$(".api-post-update-btn").on('click', (e) => {
    const liElem = $(e.target).parents("li");
    const updateBoardNo = $("#post-no-to-update").val();
    if (updateBoardNo === '') {
        messageToToast('danger', "Please set the post number to be updated.");
    } else {
        $.ajax({
            type: 'put',
            url: '/api/post/' + $(e.target).data('post-no'),
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                title: liElem.find("#inputUpdateTitle").val(),
                comment: liElem.find("#textareaUpdateComment").val()
            })
        })
    }
});

$("#get-post-info-to-update-btn").on('click', () => {
    const updateBoardNo = $("#post-no-to-update").val();
    if (updateBoardNo === '') {
        messageToToast('danger', "Please set the post number to be updated.");
    } else {
        $.ajax({
            type: 'get',
            url: '/api/post/' + updateBoardNo,
            contentType: 'application/json',
            success: function (data) {
                $("#inputUpdateTitle").attr("disabled", false).val(data.title);
                $("#textareaUpdateComment").attr("disabled", false).val(data.comment);
                $.each($(".api-post-update-btn"), (index, elem) => {
                    $(elem).data("post-no", updateBoardNo);
                    // console.log($(elem).data("post-no"));
                });
            }
        })
    }
});

$(".api-post-delete-btn").on('click', (e) => {
    const deleteBoardNo = $("#post-no-to-delete").val();
    if (deleteBoardNo === '') {
        messageToToast('danger', "Please set the post number to be updated.");
    } else {
        $.ajax({
            type: 'delete',
            url: '/api/post/' + deleteBoardNo,
            contentType: 'application/json'
        })
    }
});