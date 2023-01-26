$(document).on('click', ".board-title", (e) => {
    const boardNo = $(e.target).data('bno');
    $.ajax({
        type: 'get',
        url: '/api/post',
        contentType: 'application/json',
        dataType: 'json',
        data: {
           boardNo: boardNo
        },
        success: (data) => {
            console.log(data);
            $("#postListByBoard").html("");
            data.forEach(post => {
                $("#postListByBoard").append("<li><button type='button' class='btn btn-link' data-pno='" + post.postNo + "'>" + post.title + "</button></li>")
            });
        }
    });

    const paginationNav = $("#postListByBoard").siblings("nav");
    const paginationPrev = $(paginationNav.find(".pagination li")[0]);
    console.log(paginationNav);
    console.log(paginationPrev);
    $.ajax({
        type: 'get',
        url: '/api/post/count',
        contentType: 'application/json',
        dataType: 'json',
        data: {
            boardNo: boardNo
        },
        success: (result) => {
            let page = Math.ceil(result / 10);
            console.log(page);
            for (let i = 1; i <= page; i++) {
                paginationPrev.after("<li class=\"page-item\"><button class=\"page-link\">" + i + "</button></li>");
            }
            paginationNav.removeClass("visually-hidden");
        }
    });
})
