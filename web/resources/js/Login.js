function requestLogin() {

    const requestLoginForm = document.forms.namedItem("requestLoginForm");

    // requestLoginForm.submit();

    console.log(requestLoginForm.elements.namedItem("username").value);

    const requestLoginElements = {
        username: requestLoginForm.elements.namedItem("username").value,
        password: requestLoginForm.elements.namedItem("password").value
    }

    $.post("/login", requestLoginElements, function (data, status){
        // console.log("Data: " + data);
        console.log("Status: " + status);
    });

    // sessionStorage.setItem("alertType", "Login");
    // sessionStorage.setItem("alertStatus", "SUCCESS");
}
