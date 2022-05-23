function requestLogin() {

    const requestLoginForm = document.forms.namedItem("requestLoginForm");

    const requestLoginElements = {
        username: requestLoginForm.elements.namedItem("username").value,
        password: requestLoginForm.elements.namedItem("password").value,
        "remember-me": requestLoginForm.querySelector("input[name='remember-me']").checked
    }

    let loginStatus = null;
    let loginRedirectURL = null;
    let loginUserID = null;

    $.ajax({
        url: "/login",
        type: "POST",
        dataType: "JSON",
        data: requestLoginElements,
        success: function (data) {
            loginStatus = data.status;
            loginRedirectURL = data.redirectURL;
            loginUserID = data.userID;
        },
        error: function (data) {
            loginStatus = data.status;
            loginRedirectURL = data.redirectURL;
        },
        complete: function () {
            console.log("WHERE ARE YOU");
            window.location.href = loginRedirectURL;
            // window.location.replace(loginRedirectURL);
            sessionStorage.setItem("boardAlertType", "Login");
            sessionStorage.setItem("alertStatus", loginStatus);
        }
    });


    // sessionStorage.setItem("boardAlertType", "Login");
    // sessionStorage.setItem("alertStatus", "SUCCESS");
}
