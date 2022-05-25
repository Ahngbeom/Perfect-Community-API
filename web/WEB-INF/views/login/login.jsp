<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-04-30
  Time: 오후 9:37
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<div class="container">
    <h1 class="ml-lg-5">Login</h1>
    <div class="d-flex justify-content-center">
        <form action="/login/oauth2/code/google" method="get">
            <button type="submit">Google</button>
        </form>
        <form action="${pageContext.request.contextPath}/login" method="post" id="requestLoginForm">
            <%--    <input boardAlertType="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <sec:csrfInput/>
            <div class="form-group">
                <label>
                    ID
                </label>
                <input type="text" name="username" value="admin" placeholder="User ID" class="form-control"/>
            </div>
            <div class="form-group">
                <label>
                    PW
                </label>
                <input type="password" name="password" value="1234" placeholder="Password" class="form-control"/>
            </div>
            <div class="form-check">
                <input type="checkbox" name="remember-me" class="form-check-input">
                <label>
                    자동로그인
                </label>
            </div>
            <%--                <button type="button" class="btn btn-primary" id="requestLoginBtn" onclick="requestLogin()">Sign in</button>--%>
            <button type="submit" class="btn btn-primary" id="requestLoginBtn">Sign in</button>
            <%--                <button boardAlertType="submit" class="btn btn-primary" id="requestLoginBtn">Sign in</button>--%>
            <button type="button" class="btn btn-secondary"
                    onclick="location.href='${pageContext.request.contextPath}/member/create'">
                Sign Up
            </button>
        </form>
    </div>
</div>
</body>
</html>
<%--<script src="https://accounts.google.com/gsi/client" async defer>--%>
<%--    function handleCredentialResponse(response) {--%>
<%--        console.log("Encoded JWT ID token: " + response.credential);--%>
<%--    }--%>
<%--    window.onload = function () {--%>
<%--        google.accounts.id.initialize({--%>
<%--            client_id: "YOUR_GOOGLE_CLIENT_ID",--%>
<%--            callback: handleCredentialResponse--%>
<%--        });--%>
<%--        google.accounts.id.renderButton(--%>
<%--            document.getElementById("buttonDiv"),--%>
<%--            { theme: "outline", size: "large" }  // customization attributes--%>
<%--        );--%>
<%--        google.accounts.id.prompt(); // also display the One Tap dialog--%>
<%--    }--%>
<%--</script>--%>

