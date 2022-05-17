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
        <form action="${pageContext.request.contextPath}/login" method="post">
            <%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
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
            <button type="submit" class="btn btn-primary">Sign in</button>
            <button type="button" class="btn btn-secondary"
                    onclick="location.href='${pageContext.request.contextPath}/member/create'">
                Sign Up
            </button>
        </form>
    </div>
</div>

</body>
</html>
