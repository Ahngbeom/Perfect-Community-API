export function ajaxPostRegistration(data) {
    return $.ajax({
        type: 'post',
        url: '/api/post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(data)
    });
}

$(".api-post-create-btn").on('click', (e) => {
    const liElem = $(e.target).parents("li");
    const type = liElem.find("select[name='type']").val();

    ajaxPostRegistration({
        boardNo: liElem.find("input[name='boardNo']").val(),
        type: type !== 'null' ? type : undefined,
        title: liElem.find("input[name='title']").val(),
        contents: liElem.find("textarea[name='comment']").val()
    });
});

function requireLogin(requestFormElement) {
    sessionStorage.setItem(
        savedRequestSessionKey,
        JSON.stringify(
            new SavedRequest(
                "post",
                "/api/post",
                {
                    boardNo: requestFormElement.find("input[name='boardNo']").val(),
                    type: requestFormElement,
                    title: requestFormElement.find("input[name='title']").val(),
                    contents: requestFormElement.find("textarea[name='comment']").val()
                }
            )
        )
    );

    loginSubmitBtn.on('click', () => {
        console.log(
            {
                userId: loginModalElem.find("input[name='username']").val(),
                userPw: loginModalElem.find("input[name='password']").val()
            }
        )
        $.when(ajaxLogin())
            .done(() => {
                loginModal.hide();
                loginModalElem.on('hidden.bs.modal', () => {
                    if (confirm("사용자 정보에 변동사항이 발생하여 새로고침합니다.")) {
                        location.reload();
                    } else {
                        location.reload();
                    }
                });
            })
            .fail((response) => {
                console.error(response);
            });
    });
    loginModal.show();
}
