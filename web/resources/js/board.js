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