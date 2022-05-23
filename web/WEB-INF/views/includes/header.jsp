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
    <title>BasicSpringMVC</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <%--    <link rel="stylesheet" boardAlertType="text/css" href="${pageContext.request.contextPath}/resources/css/board.css"/>--%>
</head>
<body>
<div class="container-fluid">
    <nav class="nav nav-pills nav-fill w-100 font-weight-bold">
        <a class="nav-item nav-link active w-25" href="${pageContext.request.contextPath}/">Home</a>
        <sec:authorize access="isAuthenticated()" var="isAuthorizeAny">
            <sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin"/>
            <sec:authentication property="principal.member.userId" var="principalUserId"/>
        </sec:authorize>
        <div class="nav-item nav-link alert-primary w-50" role="alert" id="serverMessage">
            <c:if test="${isAdmin}">
                Hello 👑[${principalUserId}]👑
            </c:if>
        </div>
        <c:choose>
            <c:when test="${isAuthorizeAny}">
                <button class="nav-item nav-link w-25 btn btn-warning font-weight-bold"
                        onclick="requestLogout()">
                    Logout
                </button>
            </c:when>
            <c:otherwise>
                <button class="nav-item nav-link w-25 btn btn-info font-weight-bold"
                        onclick="location.href='${pageContext.request.contextPath}/login'">
                    Login
                </button>
            </c:otherwise>
        </c:choose>
    </nav>
    <sec:authorize access="isAuthenticated()" var="isAuthorizeAny">
        <div class="d-flex justify-content-center w-100">
            <div class="w-100">
                <button class="btn btn-secondary w-100" type="button" data-toggle="collapse"
                        data-target="#collapseExample"
                        aria-expanded="false" aria-controls="collapseExample">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="16" height="16">
                        <path fill-rule="evenodd"
                              d="M12.78 6.22a.75.75 0 010 1.06l-4.25 4.25a.75.75 0 01-1.06 0L3.22 7.28a.75.75 0 011.06-1.06L8 9.94l3.72-3.72a.75.75 0 011.06 0z"></path>
                    </svg>
                </button>
                <div class="collapse" id="collapseExample">
                    <button class="btn btn-dark w-100"
                            onclick="location.href='${pageContext.request.contextPath}/member/info'">My Account
                    </button>
                        <%--                    <sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin">--%>
                    <c:if test="${isAdmin}">
                        <button class="btn btn-dark w-100" onclick="location.href='/member/list'">회원 목록 보기</button>
                    </c:if>
                        <%--                    </sec:authorize>--%>
                </div>
            </div>
        </div>
    </sec:authorize>
</div>
<hr>
<div class="container-fluid">
    <div class="nav">
        <c:if test="${not empty pageHeader}">
            <a class="h1 nav-link active"
               href="${pageContext.request.contextPath}${pageHeader.link}">${pageHeader.title}</a>
            <h3 class="text-justify">${pageHeader.message}</h3>
        </c:if>
    </div>
    <sec:authorize access="isAuthenticated()" var="isAuthenticated"/>
</div>

<!-- Modal -->
<div class="modal fade" id="centerModal" tabindex="-1" role="dialog"
     aria-labelledby="centerModalTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="centerModalTitle" >Undefined</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" id="centerModalSubmit">Undefined</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
