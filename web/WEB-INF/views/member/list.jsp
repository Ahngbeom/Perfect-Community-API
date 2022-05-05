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
                    <fmt:parseDate value="${Member.updateDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedUpdateDate" type="both"/>
                    <fmt:formatDate value="${parsedUpdateDate}" pattern="yyyy년 MM월 dd일"/>

                </td>
                <td>
                    <c:forEach items="${Member.authList}" var="authList" varStatus="status">
                        ${authList.auth}
                        <c:if test="${status.count ne 1}">
                            ,
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                        ${Member.enabled}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</sec:authorize>
</body>
</html>
