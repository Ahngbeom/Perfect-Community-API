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
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div>
        <div>
            <table>
                <thead>
                <tr>
                    <th>
                        ID
                    </th>
                    <th>
                        Name
                    </th>
                    <th>
                        마지막 수정 일자
                    </th>
                    <th>
                        권한
                    </th>
                    <th>
                        활성화
                    </th>
                    <th>
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
                            <fmt:formatDate value="${parsedUpdateDate}" pattern="yyyy년 MM월 dd일"/>

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
                                <input type="button" class="admin-delete-auth-btn" value="특정 권한 삭제"/>
                                <input type="button" class="admin-deleteAll-auth-btn" value="모든 권한 삭제"/>
                                <c:choose>
                                    <c:when test="${isAdmin}">
                                        <input type="button" class="admin-delete-member-btn" value="계정 삭제" disabled/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="button" class="admin-delete-member-btn" value="계정 삭제"/>
                                    </c:otherwise>
                                </c:choose>

                                <!-- Modal -->
                                <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                ...
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                <button type="button" class="btn btn-primary">Save changes</button>
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
            <div>
                <input type="button" value="모든 계정 삭제"/>
            </div>
        </div>
        <div class="">
            <div class="border-black">
                <h5>Member Register</h5>
                <form method="post" action="${pageContext.request.contextPath}/member/create" id="MemberRegisterForm">
                    <table width="100%">
                        <tbody>
                        <tr>
                            <th>
                                ID
                            </th>
                            <td>
                                <input type="text" name="userId" value-status="error" class="input-w100"/>
                                <p id="member-form-userId-status" class=""></p>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                PW
                            </th>
                            <td>
                                <input type="password" name="password" value-status="error" class="input-w100"/>
                                <input type="hidden" id="passwordReconfirm" value-status="ERROR" class="input-w100"/>
                                <p id="member-form-password-status" class="" ></p>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Name
                            </th>
                            <td>
                                <input type="text" name="userName" value-status="error" class="input-w100"/>
                                <p id="member-form-userName-status" class="" ></p>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Authority
                            </th>
                            <td>
                                <select name="auth" class="input-w100">
                                    <option value="ROLE_USER">
                                        USER
                                    </option>
                                    <option value="ROLE_ADMIN">
                                        ADMIN
                                    </option>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="display-flex-center" >
                        <input type="button" value="계정 생성"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</sec:authorize>
</body>
</html>
