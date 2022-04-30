<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-04-07
  Time: 오전 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <%--    <title>Title</title>--%>
    <%--    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div>
    <a href="/login">Login</a>
    <form action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="submit" value="Logout">
    </form>
</div>
<div>
<%--    <p><sec:authentication property="principal" /></p>--%>
<%--    <p><sec:authentication property="principal.member" /></p>--%>
<%--    <p><sec:authentication property="principal" /></p>--%>
<%--    <p><sec:authentication property="principal" /></p>--%>
</div>
<hr>
<div>
    <a href="/all">All</a>
    <a href="/member">Member</a>
    <a href="/admin">Admin</a>
</div>
</body>
