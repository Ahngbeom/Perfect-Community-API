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
    <div class="display-flex-between" style="width:100%">
        <div style="width:60%">
            <table style="table-layout: fixed">
                <colgroup>
                    <col width="70px">
                    <col width="120px">
                    <col width="150px">
                    <col width="110px">
                    <col width="70px">
                    <col width="110px">
                </colgroup>
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
                    <th style="width: 20px; white-space: nowrap;">
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
                            <form method="post" action="">
                                <sec:csrfInput/>
                                <input type="hidden" value="${Member.userId}" name="userId"/>
                                <input type="button" class="admin-delete-auth-btn" value="특정 권한 삭제"/>
                                <input type="button" class="admin-deleteAll-auth-btn" value="모든 권한 삭제"/>
                                <input type="button" class="admin-delete-member-btn" value="계정 삭제"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div>
                <input type="button" value="모든 계정 삭제"/>
            </div>
        </div>
        <div class="" style="width:40%">
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
                                <p id="member-form-userId-status" class="" style="font-size: x-small;"></p>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                PW
                            </th>
                            <td>
                                <input type="password" name="password" value-status="error" class="input-w100"/>
                                <input type="hidden" id="passwordReconfirm" value-status="ERROR" class="input-w100"/>
                                <p id="member-form-password-status" class="" style="font-size: x-small;"></p>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Name
                            </th>
                            <td>
                                <input type="text" name="userName" value-status="error" class="input-w100"/>
                                <p id="member-form-userName-status" class="" style="font-size: x-small;"></p>
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
                    <div class="display-flex-center" style="margin: 10px">
                        <input type="button" value="계정 생성"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</sec:authorize>
</body>
</html>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", () => {
        const adminDeleteAuthBtn = document.querySelectorAll(".admin-delete-auth-btn");
        const adminDeleteAllAuthBtn = document.querySelectorAll(".admin-deleteAll-auth-btn");
        const adminDeleteMemBtnCollect = document.querySelectorAll(".admin-delete-member-btn");


        adminDeleteMemBtnCollect.forEach(adminDeleteMemBtn => {
            adminDeleteMemBtn.addEventListener("click", evt => {
                let form = adminDeleteMemBtn.closest("form");
                form.action = "/member/remove";
                form.submit();
            });
        })

    });
</script>