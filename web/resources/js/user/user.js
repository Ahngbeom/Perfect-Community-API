$("#api-user-info-btn").on('click', () => {
    if (userAuthentication == null) {
        responseArea.html("Not logged in.").css("color", "red");
    } else {
        $.ajax({
            type: 'get',
            url: '/api/user/' + userAuthentication.username,
            contentType: 'application/json',
            success: (data) => {
                responseArea.html(JSON.stringify(data)).css('word-break', 'break-all');
            },
            error: (xhr) => {
                console.error(xhr.responseText);
            }
        })
    }
});

$("#api-user-list-btn").on('click', () => {
    $.ajax({
        type: 'get',
        url: '/api/user',
        contentType: 'application/json',
        success: (users) => {
            responseArea.html("");
            users.forEach(user => {
                responseArea.append("<li>" + JSON.stringify(user) + "</li>").css('word-break', 'break-all');
            });
        },
        error: (xhr) => {
            console.error(xhr.responseText);
        }
    })
});

$("#api-user-signup-btn").on('click', () => {
    const signupForm = $("#api-user-signup-form");
    $.ajax({
        type: 'post',
        url: '/api/user',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
            userId: signupForm.find("input[name='userId']")[0].value,
            password: signupForm.find("input[name='password']")[0].value,
            nickname: signupForm.find("input[name='nickname']")[0].value
        }),
        success: (data) => {
            console.log(data);
            // responseArea.html("");
            // users.forEach(user => {
            //     responseArea.append("<li>" + JSON.stringify(user) + "</li>").css('word-break', 'break-all');
            // });
        },
        error: (xhr) => {
            console.error(xhr.responseText);
            responseArea.html(xhr.responseText).css("color", "red");
        }
    })
});
