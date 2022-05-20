function requestLogout() {
    let logoutForm = document.createElement('form');
    logoutForm.method = 'POST';
    logoutForm.action = '/logout';
    document.body.appendChild(logoutForm);
    logoutForm.submit();
    sessionStorage.setItem("alertType", "Logout");
    sessionStorage.setItem("alertStatus", "SUCCESS");
}