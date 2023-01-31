function loadAuthentication() {
    console.log("Username", username);
    console.log("Access Token", accessToken);
    if (username !== undefined && accessToken !== undefined) {
        $("#authentication span").html("Authenticated (" + username + ")").css("color", "green");
        $("#jwtInfo").removeClass("visually-hidden");
        $("#loginForm").addClass("visually-hidden");
        $("#logoutBtn").removeClass("visually-hidden");
    } else {
        $("#authentication span").html("Not Authenticated").css("color", "red");
        $("#jwtInfo").addClass("visually-hidden");
        $("#loginForm").removeClass("visually-hidden");
        $("#logoutBtn").addClass("visually-hidden");
    }
}

$("#loginBtn").on('click', () => {
    $.ajax({
        type: 'post',
        url: '/api/login',
        async: false,
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
            username: $("input[name='username']").val(),
            password: $("input[name='password']").val()
        }),
        success: (data, status, xhr) => {
            username = data.username;
            accessToken = xhr.getResponseHeader("Authorization").substring("Bearer ".length);
            loadAuthentication();
            putJwtInfo(data);
        },
        error: (xhr) => {
            alert(xhr.responseText);
        }
    })
});

$("#logoutBtn").on('click', () => {
    $.ajax({
        type: 'post',
        url: '/api/logout',
        async: false,
        contentType: 'application/json',
    }).done((data) => {
        console.log(data);
        username = undefined;
        accessToken = undefined;
        clearInterval(accessTokenInterval);
        clearInterval(refreshTokenInterval);
    }).fail((xhr) => {
        console.error(xhr);
        console.error(xhr.responseText);
    }).always((jqXHR) => {
        loadAuthentication();
    });
});