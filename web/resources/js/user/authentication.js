let userAuthentication = null;

function getAuthentication() {
    $.ajax({
        type: "get",
        url: "/api/authentication",
        contentType: "application/json",
        success: (data) => {
            userAuthentication = data;
            console.log("User Authentication", userAuthentication);
            console.log(sessionStorage.getItem(userAuthentication.sessionId));
        },
        error: (xhr) => {
            console.error(xhr.responseText);
            responseArea.html(xhr.responseText).css("color", "red");
        }
    })
}