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
        },
        error: (xhr) => {
            console.error(xhr.responseText);
            userState.html(xhr.responseText).css("color", "red");
        }
    })
}

$("#api-authentication-btn").on('click', (e) => {
   getAuthentication(true);
});

