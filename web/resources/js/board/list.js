function ajaxGetBoardList() {
    return $.ajax({
        type: 'get',
        url: '/api/board',
        contentType: 'application/json'
    })
}
