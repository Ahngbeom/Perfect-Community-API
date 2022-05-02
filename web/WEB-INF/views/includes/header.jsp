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
    <c:choose>
        <c:when test="${not empty title}">
            <title>${title}</title>
        </c:when>
        <c:otherwise>
            <title>BasicSpringMVC</title>
        </c:otherwise>
    </c:choose>
    <%--    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div>
    <sec:authorize access="isAnonymous()">
        <a href="${pageContext.request.contextPath}/login">Login</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <sec:csrfInput/>
<%--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>
            <input type="submit" value="Logout">
        </form>
    </sec:authorize>
</div>
<div>
    <sec:authorize access="isAuthenticated()">
        <p><sec:authentication property="principal"/></p>
        <p><sec:authentication property="principal.member"/></p>
        <p><sec:authentication property="principal.member.userName"/></p>
        <p><sec:authentication property="principal.member.userId"/></p>
        <p><sec:authentication property="principal.member.authList"/></p>
    </sec:authorize>
</div>
<hr>
<div>
    <a href="${pageContext.request.contextPath}/all">All</a>
    <a href="${pageContext.request.contextPath}/member">Member</a>
    <a href="${pageContext.request.contextPath}/login/admin">Admin</a>
</div>
</body>
