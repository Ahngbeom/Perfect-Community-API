<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-05-03
  Time: 오후 10:08
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-fluid">
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="d-flex justify-content-between w-100">
            <div class="w-75">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th scope="col">
                            ID
                        </th>
                        <th scope="col">
                            Name
                        </th>
                        <th scope="col">
                            마지막 수정 일자
                        </th>
                        <th scope="col">
                            권한
                        </th>
                        <th scope="col">
                            활성화
                        </th>
                        <th scope="col">
                            제어
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${MemberList}" var="Member" varStatus="status">
                        <tr>
                            <td>
                                    ${Member.userId}
                            </td>
                            <td>
                                    ${Member.userName}
                            </td>
                                <%--                <td>--%>
                                <%--                        ${Member.password}--%>
                                <%--                </td>--%>
                                <%--                <td>--%>
                                <%--                        ${Member.regDate}--%>
                                <%--                </td>--%>
                            <td>
                                <fmt:parseDate value="${Member.updateDate}" pattern="yyyy-MM-dd'T'HH:mm"
                                               var="parsedUpdateDate"
                                               type="both"/>
                                <fmt:formatDate value="${parsedUpdateDate}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>

                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty Member.authList or Member.authList ne null}">
                                        <c:forEach items="${Member.authList}" var="authList" varStatus="status">
                                            <c:choose>
                                                <c:when test="${fn:length(authList.auth) gt 10}">
                                                    ${fn:substring(authList.auth, 0, 10)}
                                                </c:when>
                                                <c:when test="${not empty authList.auth or authList.auth ne null}">
                                                    ${authList.auth}
                                                </c:when>
                                                <c:otherwise>
                                                    권한 없음
                                                </c:otherwise>
                                            </c:choose>
                                            <c:if test="${status.count ne 1}">
                                                ,
                                            </c:if>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        권한 없음
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                    ${Member.enabled}
                            </td>
                            <td>
                                <c:forEach items="${Member.authList}" var="authList">
                                    <c:if test="${authList.auth == 'ROLE_ADMIN'}">
                                        <c:set var="isAdmin" value="${authList.auth eq 'ROLE_ADMIN'}"/>
                                    </c:if>
                                </c:forEach>
                                <form method="post" action="">
                                    <sec:csrfInput/>
                                    <input type="hidden" value="${Member.userId}" name="userId"/>
                                    <input type="button" class="btn btn-secondary" data-toggle="modal"
                                           data-target="#exampleModalCenter" value="특정 권한 삭제"/>
                                    <input type="button" class="btn btn-dark" value="모든 권한 삭제"/>
                                    <c:choose>
                                        <c:when test="${isAdmin}">
                                            <button type="button" class="btn btn-warning" disabled>비활성화</button>
                                            <input type="button" class="btn btn-danger" value="계정 삭제"
                                                   disabled/>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="button" class="btn btn-warning">비활성화</button>
                                            <input type="button" class="btn btn-danger" value="계정 삭제"/>
                                        </c:otherwise>
                                    </c:choose>

                                    <!-- Modal -->
                                    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                                         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLongTitle">특정 권한 삭제</h5>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="checkbox"
                                                               id="inlineCheckbox1" value="ROLE_ADMIN">
                                                        <label class="form-check-label"
                                                               for="inlineCheckbox1">ROLE_ADMIN</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="checkbox"
                                                               id="inlineCheckbox2" value="ROLE_MANAGER">
                                                        <label class="form-check-label" for="inlineCheckbox2">ROLE_MANAGER</label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="checkbox"
                                                               id="inlineCheckbox3" value="ROLE_USER">
                                                        <label class="form-check-label"
                                                               for="inlineCheckbox3">ROLE_USER</label>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-danger">삭제</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <c:remove var="isAdmin"/>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="display-flex-right">
                    <input type="button" value="모든 계정 삭제"/>
                </div>
            </div>
            <div class="w-25 p-xl-5">
                <h5>Member Register</h5>
<%--                <form method="post" action="${pageContext.request.contextPath}/member/create"--%>
<%--                      id="MemberRegisterForm">--%>
<%--                    <table>--%>
<%--                        <tbody>--%>
<%--                        <tr>--%>
<%--                            <th>--%>
<%--                                ID--%>
<%--                            </th>--%>
<%--                            <td>--%>
<%--                                <input type="text" name="userId" value-status="error" class=""/>--%>
<%--                                <p id="member-form-userId-status" class=""></p>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <th>--%>
<%--                                PW--%>
<%--                            </th>--%>
<%--                            <td>--%>
<%--                                <input type="password" name="password" value-status="error" class=""/>--%>
<%--                                <input type="hidden" id="passwordReconfirm" value-status="ERROR"--%>
<%--                                       class="input-w100"/>--%>
<%--                                <p id="member-form-password-status" class=""></p>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <th>--%>
<%--                                Name--%>
<%--                            </th>--%>
<%--                            <td>--%>
<%--                                <input type="text" name="userName" value-status="error" class=""/>--%>
<%--                                <p id="member-form-userName-status" class=""></p>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <th>--%>
<%--                                Authority--%>
<%--                            </th>--%>
<%--                            <td>--%>
<%--                                <select name="auth" class="">--%>
<%--                                    <option value="ROLE_USER">--%>
<%--                                        USER--%>
<%--                                    </option>--%>
<%--                                    <option value="ROLE_ADMIN">--%>
<%--                                        ADMIN--%>
<%--                                    </option>--%>
<%--                                </select>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                        </tbody>--%>
<%--                    </table>--%>
<%--                    <div class="display-flex-center">--%>
<%--                        <input type="button" value="계정 생성"/>--%>
<%--                    </div>--%>
<%--                </form>--%>
                <form method="post" action="${pageContext.request.contextPath}/member/create" id="MemberRegisterForm">
                    <div class="form-group">
                        <label>ID</label>
                        <input type="text" name="userId" value-status="error" class="form-control" placeholder="User ID"/>
                        <p id="member-form-userId-status" class=""></p>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="password" class="form-control" placeholder="Password">
                        <input type="hidden" id="passwordReconfirm" value-status="error" class="form-control"
                               placeholder="Password Reconfirm"/>
                        <p id="member-form-password-status" class=""></p>
                    </div>
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" name="userName" class="form-control" value-status="error" placeholder="User Name">
                        <p id="member-form-userName-status" class=""></p>
                    </div>
                    <div class="form-group">
                        <label>Authority</label>
                        <select name="auth" class="form-control">
                            <option value="ROLE_USER">
                                USER
                            </option>
                            <option value="ROLE_ADMIN">
                                ADMIN
                            </option>
                        </select>
                    </div>
                    <button type="button" class="btn btn-info" id="MemberCreateSubmitBtn">계정 생성</button>
                </form>
            </div>
        </div>
    </sec:authorize>
</div>
</body>
</html>
