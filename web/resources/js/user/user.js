$("#api-user-info-btn").on('click', (e) => {
    $.ajax({
        type: 'get',
        url: '/api/user/' + $(e.target).data('userid'),
        contentType: 'application/json',
        success: (data) => {
            console.log(data);
            // responseArea.html(JSON.stringify(data)).css('word-break', 'break-all');
        },
        error: (xhr) => {
            console.error(xhr.responseText);
        }
    })
});

$("#api-user-list-btn").on('click', () => {
    $.ajax({
        type: 'get',
        url: '/api/user',
        contentType: 'application/json',
        success: (users) => {
            console.log(users);
            // responseArea.html("");
            // users.forEach(user => {
            //     responseArea.append("<li>" + JSON.stringify(user) + "</li>").css('word-break', 'break-all');
            // });
        },
        error: (xhr) => {
            console.error(xhr.responseText);
        }
    })
});

$(".api-user-signup-btn").on('click', () => {
    const signupForm = $("#api-user-signup-form");
    $.ajax({
        type: 'post',
        url: '/api/user',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
            userId: signupForm.find("input[name='userId']")[0].value,
            password: signupForm.find("input[name='password']")[0].value,
            nickname: signupForm.find("input[name='nickname']")[0].value,
            authority: signupForm.find("input[name='authority']:checked").val()
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
        }
    })
});
