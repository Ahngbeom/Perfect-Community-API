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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/board.css"/>--%>
</head>
<body>
<div class="container">
    <div class="d-flex justify-content-between">
        <h3><a class="page-link" href="${pageContext.request.contextPath}/">Home</a></h3>
        <div>
            <sec:authorize access="isAnonymous()">
                <button class="btn btn-info" onclick="location.href='${pageContext.request.contextPath}/login'">Login
                </button>
                <br><a href="${pageContext.request.contextPath}/member/create">Sign Up</a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()" var="isAuthorizeAny">
                <sec:authentication property="principal.member.userId" var="principalUserId"></sec:authentication>
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <sec:csrfInput/>
                    <input class="btn btn-warning" type="submit" value="Logout">
                </form>
                <a class="page-link" href="${pageContext.request.contextPath}/member/info">My Account</a>
            </sec:authorize>
        </div>
    </div>

    <div class="d-flex justify-content-between">
        <h2 class="text-capitalize" id="serverMessage"></h2>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <button class="page-link" onclick="location.href='/member/list'">회원 목록 보기</button>
        </sec:authorize>
    </div>

    <div>

    </div>
    <hr>
</div>
</body>
</html>