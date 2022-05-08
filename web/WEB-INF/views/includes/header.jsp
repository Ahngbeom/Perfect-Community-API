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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <%--        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/boardForm.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/board.css"/>
</head>
<body>
<div class="display-flex-between">
    <h3><a href="${pageContext.request.contextPath}/">Home</a></h3>
    <div>
        <sec:authorize access="isAnonymous()">
            <button onclick="location.href='${pageContext.request.contextPath}/login'">Login</button>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <sec:csrfInput/>
                <input type="submit" value="Logout">
            </form>
            <a href="${pageContext.request.contextPath}/member/info">My Account</a>
        </sec:authorize>
    </div>
</div>

<div>
    <h2 id="serverMessage"></h2>
</div>

<div>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <button onclick="location.href='/member/list'">회원 목록 보기</button>
    </sec:authorize>
</div>
<hr>
<%--<div>--%>
<%--    <a href="${pageContext.request.contextPath}/all">All</a>--%>
<%--    <a href="${pageContext.request.contextPath}/member">Member</a>--%>
<%--    <a href="${pageContext.request.contextPath}/login/admin">Admin</a>--%>
<%--</div>--%>
<%--<hr>--%>

</body>
