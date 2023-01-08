$("#api-board-list-btn").on('click', () => {
    $.ajax({
        type: 'get',
        url: '/api/board',
        contentType: 'application/json'
    })
});

$("#api-board-info-btn").on('click', (e) => {
    $.ajax({
        type: 'get',
        url: '/api/board/' + $(e.target).siblings("label").children("input[type='number']").val(),
        contentType: 'application/json'
    })
});

$(".api-board-create-btn").on('click', (e) => {
    const liElem = $(e.target).parents("li");
    $.ajax({
        type: 'post',
        url: '/api/board',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
            title: liElem.find("input[name='title']").val(),
            comment: liElem.find("textarea[name='comment']").val()
        })
    })
});

$(".api-board-update-btn").on('click', (e) => {
    const liElem = $(e.target).parents("li");
    const updateBoardNo = $("#board-no-to-update").val();
    if (updateBoardNo === '') {
        messageToToast('danger', "Please set the board number to be updated.");
    } else {
        $.ajax({
            type: 'put',
            url: '/api/board/' + $(e.target).data('board-no'),
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                title: liElem.find("#inputUpdateTitle").val(),
                comment: liElem.find("#textareaUpdateComment").val()
            })
        })
    }
});

$("#get-board-info-to-update-btn").on('click', () => {
    const updateBoardNo = $("#board-no-to-update").val();
    if (updateBoardNo === '') {
        messageToToast('danger', "Please set the board number to be updated.");
    } else {
        $.ajax({
            type: 'get',
            url: '/api/board/' + updateBoardNo,
            contentType: 'application/json',
            success: function (data) {
                $("#inputUpdateTitle").attr("disabled", false).val(data.title);
                $("#textareaUpdateComment").attr("disabled", false).val(data.comment);
                $.each($(".api-board-update-btn"), (index, elem) => {
                    $(elem).data("board-no", updateBoardNo);
                    // console.log($(elem).data("board-no"));
                });
            }
        })
    }
});

$(".api-board-delete-btn").on('click', (e) => {
    const deleteBoardNo = $("#board-no-to-delete").val();
    if (deleteBoardNo === '') {
        messageToToast('danger', "Please set the board number to be updated.");
    } else {
        $.ajax({
            type: 'delete',
            url: '/api/board/' + deleteBoardNo,
            contentType: 'application/json'
        })
    }
});