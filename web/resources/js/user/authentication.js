const userState = $("#user-state");
let userAuthentication = null;

function getAuthentication(writeResponseBody) {
    $.ajax({
        type: "get",
        url: "/api/authentication",
        contentType: "application/json",
        success: (data) => {
            userAuthentication = data;
            console.log("User Authentication", userAuthentication);
            // userState.find("button").html("로그아웃");
            userState.html("Logged in - " + userAuthentication.username).css("color", "green");
            if (writeResponseBody)
                responseArea.html(JSON.stringify(userAuthentication)).css('word-break', 'break-all');
                // responseArea.html(
                //     "<li>" +
                //     "username: <span class='fw-bold'>" + userAuthentication.username +
                //     "</span></li>" +
                //     "<li><span class='text-wrap fw-bold'>" +
                //     "password: " + userAuthentication.password +
                //     "</span></li>" +
                //     "<li>" +
                //     "authorities: <span class='fw-bold'><ul>" +
                //     userAuthentication.authorities.map(value => "<li>" + value.authority + "</li>") +
                //     "</span></ul></li>" +
                //     "<li>" +
                //     "enabled: <span class='fw-bold'>" + userAuthentication.enabled +
                //     "</span></li>" +
                //     "<li>" +
                //     "accountNonExpired: <span class='fw-bold'>" + userAuthentication.accountNonExpired +
                //     "</span></li>" +
                //     "<li>" +
                //     "accountNonLocked: <span class='fw-bold'>" + userAuthentication.accountNonLocked +
                //     "</span></li>" +
                //     "<li>" +
                //     "credentialsNonExpired: <span class='fw-bold'>" + userAuthentication.credentialsNonExpired +
                //     "</span></li>"
                // ).css('word-break', 'break-all');
        },
        error: (xhr) => {
            console.error(xhr.responseText);
            userState.html(xhr.responseText).css("color", "red");
            if (writeResponseBody)
                responseArea.html(xhr.responseText).css("color", "red");
        }
    })
}

$("#api-authentication-btn").on('click', (e) => {
   getAuthentication(true);
});

