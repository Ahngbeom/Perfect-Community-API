
function ajaxPostRemove(postNo) {
    return $.ajax({
        type: 'delete',
        url: '/api/post/' + postNo,
        contentType: 'application/json'
    })
}

$(".api-post-delete-btn").on('click', () => {
    const deletePostNo = $("#post-no-to-delete").val();
    if (deletePostNo === '') {
        messageToToast('danger', "Please set the post number to be updated.");
    } else {
        ajaxPostRemove(deletePostNo);
    }
});