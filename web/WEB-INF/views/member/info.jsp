<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-05-03
  Time: 오후 9:29
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>My Account</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <ul>
        <li>
            <p><sec:authentication property="principal"/></p>
        </li>
        <li>
            <p><sec:authentication property="principal.member"/></p>
        </li>
        <li>
            <p><sec:authentication property="principal.member.userName"/></p>
        </li>
        <li>
            <p><sec:authentication property="principal.member.userId"/></p>
        </li>
        <li>
            <p><sec:authentication property="principal.member.authList"/></p>
        </li>
    </ul>
</sec:authorize>
</body>
</html>
