export function ajaxPostModification(data) {
    console.log(data);
    console.log(JSON.stringify(data));
    return $.ajax({
        type: 'put',
        url: '/api/post/' + data.postNo,
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
            boardNo: data.boardNo,
            type: data.type,
            title: data.title,
            contents: data.contents
        })
    })
}

$(".api-post-update-btn").on('click', (e) => {
    const liElem = $(e.target).parents("li");
    const updatePostNo = liElem.find("input[name='postNo']").val();
    if (updatePostNo === '') {
        messageToToast('danger', "Please set the post number to be updated.");
    } else {
        ajaxPostModification({
            postNo: updatePostNo,
            boardNo: liElem.find("input[name='boardNo']").val(),
            type: liElem.find("select[name='type']").val(),
            title: liElem.find("input[name='title']").val(),
            contents: liElem.find("textarea[name='contents']").val()
        });
    }
});

$("#get-post-info-to-update-btn").on('click', (e) => {
    const liElem = $(e.currentTarget).parents("li");
    console.log(liElem);
    const updatePostNo = $("#post-no-to-update").val();
    if (updatePostNo === '') {
        messageToToast('danger', "Please set the post number to be updated.");
    } else {
        $.ajax({
            type: 'get',
            url: '/api/post/' + updatePostNo,
            contentType: 'application/json',
            success: function (data) {
                liElem.find("input[name='boardNo']").attr("disabled", false).val(data.boardNo),
                    liElem.find("select[name='type']").attr("disabled", false).find("option[value='" + data.type + "']").prop('selected', 'selected'),
                    liElem.find("input[name='title']").attr("disabled", false).val(data.title),
                    liElem.find("textarea[name='contents']").attr("disabled", false).val(data.contents)
            }
        })
    }
});