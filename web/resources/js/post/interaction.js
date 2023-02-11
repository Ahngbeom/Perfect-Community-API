function increaseViews(postNo) {
    $.ajax({
        type: 'patch',
        url: '/api/post/views/' + postNo,
        async: false
    });
}



export {increaseViews}