function ajaxGetPostList(page) {
    if (page === undefined) {
        return $.ajax({
            type: 'get',
            url: '/api/post',
            contentType: 'application/json'
        });
    } else {
        return $.ajax({
            type: 'get',
            url: '/api/post',
            contentType: 'application/json',
            dataType: 'json',
            data: {
                page: page,
            }
        })
    }
}

$("#api-post-list-btn").on('click', ajaxGetPostList);
