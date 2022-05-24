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
    <div class="d-block w-100">
        <div class="w-100">
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
                                            <%--                                            <c:when test="${fn:length(authList.auth) gt 10}">--%>
                                            <%--                                                ${fn:substring(authList.auth, 0, 10)}--%>
                                            <%--                                            </c:when>--%>
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
                                <c:if test="${authList.auth == 'ROLE_USER'}">
                                    <c:set var="isUser" value="${authList.auth ne 'ROLE_ADMIN'}"/>
                                </c:if>
                            </c:forEach>
                            <form method="post" action="">
                                <sec:csrfInput/>
                                <input type="hidden" value="${Member.userId}" name="userId"/>
                                <button type="button" class="btn btn-outline-info admin-update-auth-btn">권한 변경</button>
                                <button type="button" class="btn btn-outline-danger admin-deleteAll-auth-btn">모든 권한 삭제
                                </button>
                                <c:if test="${isUser}">

                                    <c:choose>
                                        <c:when test="${Member.enabled}">
                                            <button type="button" class="btn btn-outline-warning admin-able-member-btn"
                                                    enabled="${Member.enabled}">
                                                비활성화
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="button" class="btn btn-outline-success admin-able-member-btn"
                                                    enabled="${Member.enabled}">
                                                활성화
                                            </button>
                                        </c:otherwise>
                                    </c:choose>

                                    <input type="button" class="btn btn-outline-danger admin-delete-member-btn"
                                           value="계정 삭제"/>
                                </c:if>
                            </form>
                            <c:remove var="isUser"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="float-right">
                <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#modalMemberCreate">
                    계정 추가
                </button>
                <button type="button" class="btn btn-danger">모든 계정 삭제</button>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="modalMemberCreate" tabindex="-1" role="dialog"
             aria-labelledby="modalMemberCreateTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <form method="post" action="${pageContext.request.contextPath}/member/create"
                          id="MemberRegisterForm">

                        <div class="modal-header">
                            <h5 class="modal-title" id="modalMemberCreateTitle">Member Register</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>ID</label>
                                <input type="text" name="userId" value-status="error" class="form-control"
                                       placeholder="User ID"/>
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
                                <input type="text" name="userName" class="form-control" value-status="error"
                                       placeholder="User Name">
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
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-info" id="MemberCreateSubmitBtn">계정 생성</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
