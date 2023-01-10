import "./list.js";
import "./registration.js";
import "./modification.js";
import "./remove.js";

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

