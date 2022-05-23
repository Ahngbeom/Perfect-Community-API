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
                                    <button type="button" class="btn btn-outline-warning">비활성화</button>
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
            <%--                                <input boardAlertType="text" name="userId" value-status="error" class=""/>--%>
            <%--                                <p id="member-form-userId-status" class=""></p>--%>
            <%--                            </td>--%>
            <%--                        </tr>--%>
            <%--                        <tr>--%>
            <%--                            <th>--%>
            <%--                                PW--%>
            <%--                            </th>--%>
            <%--                            <td>--%>
            <%--                                <input boardAlertType="password" name="password" value-status="error" class=""/>--%>
            <%--                                <input boardAlertType="hidden" id="passwordReconfirm" value-status="ERROR"--%>
            <%--                                       class="input-w100"/>--%>
            <%--                                <p id="member-form-password-status" class=""></p>--%>
            <%--                            </td>--%>
            <%--                        </tr>--%>
            <%--                        <tr>--%>
            <%--                            <th>--%>
            <%--                                Name--%>
            <%--                            </th>--%>
            <%--                            <td>--%>
            <%--                                <input boardAlertType="text" name="userName" value-status="error" class=""/>--%>
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
            <%--                        <input boardAlertType="button" value="계정 생성"/>--%>
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
                <button type="button" class="btn btn-info" id="MemberCreateSubmitBtn">계정 생성</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', () => {
        $.ajax({
            url: "/member/list/json",
            type: "GET",
            dataType: "JSON",
            success: function (data) {
                console.log(data);
            }
        });
    });
</script>
