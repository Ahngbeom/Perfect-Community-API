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
    <div class="display-flex-between">
        <div>
            <table width="100" style="table-layout: fixed">
                <colgroup>
                    <col width="120px">
                    <col width="120px">
                    <col width="150px">
                    <col width="110px">
                    <col width="50px">
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
        <div>
            <div class="border-black">
                <form method="post" action="${pageContext.request.contextPath}/member/create">
                    <div>
                        <label>
                            ID: <input type="text" name="userId"/> <%-- ID 중복 검사 --%>
                        </label>
                    </div>
                    <div>
                        <label>
                            PW: <input type="password" name="password"/>
                        </label>
                    </div>
                    <div>
                        <label>
                            Name: <input type="text" name="userName"/>
                        </label>
                    </div>
                    <div>
                        <label> Authority:
                            <select name="auth">
                                <option value="ROLE_USER">
                                    USER
                                </option>
                                <option value="ROLE_ADMIN">
                                    ADMIN
                                </option>
                            </select>
                        </label>
                    </div>
                    <div>
                        <input class="html-editor-align-right" type="submit" value="계정 생성"/>
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