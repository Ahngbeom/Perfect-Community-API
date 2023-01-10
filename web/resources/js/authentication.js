const userState = $("#user-state");
let userAuthentication = null;
let userID = userState.find("#user-id").text();

function getAuthentication() {
    $.ajax({
        type: "get",
        url: "/api/authentication",
        contentType: "application/json",
        success: (data) => {
            userAuthentication = data;
            console.log("User Authentication", userAuthentication);
            // userState.find("button").html("로그아웃");
        },
        error: (xhr) => {
            console.error(xhr.responseText);
        }
    })
}

$("#api-authentication-btn").on('click', (e) => {
   getAuthentication(true);
});

