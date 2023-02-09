export const postScrapBtn = postForm.find("#postScrapBtn");

postScrapBtn.on('click', () => {
    if (!postScrapBtn.hasClass('active')) {
        $.ajax({
            type: 'post',
            url: '/api/post/scrap/' + postForm.data('post-no')
        }).done(() => {
            postScrapBtn.addClass('active');
        }).fail((jqXHR) => {
            alert(jqXHR.responseText);
        });
    } else {
        $.ajax({
            type: 'delete',
            url: '/api/post/release-scrap/' + postForm.data('post-no')
        }).done(() => {
            postScrapBtn.removeClass('active');
        }).fail((jqXHR) => {
            alert(jqXHR.responseText);
        });
    }
});