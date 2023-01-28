getBoardList();

function getBoardList() {
    return $.ajax({
        type: 'get',
        url: '/api/board',
        success: (data) => {
            // console.log(data);
            data.forEach(board => {
                $("#boardList ul").append("<li><button type='button' class='btn btn-link board-title' data-bno='" + board.bno + "'>" + board.title + "</button></li>")
            });
        }
    });
}

function getBoard(bno) {
    if (bno === undefined)
        return;
    return $.ajax({
        type: 'get',
        url: '/api/board/' + bno
    });
}

$("#boardInfoBtn").on('click', () => {
    getBoard(JSON.parse($.cookie(FILTER_OPTIONS_KEY)).boardNo)
        .done((data) => {
            $.cookie(FILTER_OPTIONS_KEY, JSON.stringify({
                boardNo: data.bno,
                pageType: 'read'
            }))
            postsByBoard.html(putBoardFormHTML(data));
        });
});

const putBoardFormHTML = ((boardData) => {
    console.log(boardData);
    return "<div class='p-3'>" +
        "<div class=\"mb-3\">\n" +
        "  <label for=\"exampleFormControlInput1\" class=\"form-label\">Title</label>\n" +
        "  <input type=\"text\" class=\"form-control\" id=\"exampleFormControlInput1\" value='" + boardData.title + "'>\n" +
        "</div>\n" +
        "<div class=\"mb-3\">\n" +
        "  <label for=\"exampleFormControlTextarea1\" class=\"form-label\">Comment</label>\n" +
        "  <textarea class=\"form-control\" id=\"exampleFormControlTextarea1\" rows=\"3\">" + boardData.comment + "</textarea>\n" +
        "</div>" +
        "<div class=\"mb-3\">\n" +
        "  <button class=\"btn btn-warning\">Update</button>\n" +
        "  <button class=\"btn btn-danger\">Remove</button>\n" +
        "</div>" +
        "</div>"
});