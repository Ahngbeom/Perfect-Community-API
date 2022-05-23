function requestLogout() {
    // let logoutForm = document.createElement('form');
    // logoutForm.method = 'POST';
    // logoutForm.action = '/logout';
    // document.body.appendChild(logoutForm);
    // logoutForm.submit();
    // sessionStorage.setItem("boardAlertType", "Logout");
    // sessionStorage.setItem("alertStatus", "SUCCESS");

    let logoutStatus = null;
    let logoutRedirectURL = null;

    $.ajax({
        url: "/logout",
        type: "POST",
        dataType: "JSON",
        success: function (data) {
            logoutStatus = data.status;
            logoutRedirectURL = data.redirectURL;
        },
        error: function (data) {
            logoutStatus = data.status;
            logoutRedirectURL = data.redirectURL;
        },
        complete: function () {
            window.location.href = logoutRedirectURL;
            sessionStorage.setItem("boardAlertType", "Logout");
            sessionStorage.setItem("alertStatus", logoutStatus);
        }
    });
}