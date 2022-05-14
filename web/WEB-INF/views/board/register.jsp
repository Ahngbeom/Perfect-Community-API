<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-03-30
  Time: 오후 10:37
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Board - Register</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/boardForm.js"></script>
</head>

<body>

<h1>Board Register</h1>
<form method="post" action="${pageContext.request.contextPath}/board/register" id="boardForm">
    <table>
        <thead>

        </thead>
        <tbody>
        <tr>
            <td>작성자</td>
            <td>
                <label>
                    <sec:authorize access="isAuthenticated()" var="isAuthenticated"/>
                    <c:choose>
                        <c:when test="${isAuthenticated}">
                            <sec:authentication property="principal.member" var="memberInfo"/>
                            <input type="text" name="writer" value="${memberInfo.userName}" readonly/>
                        </c:when>
                        <c:otherwise>
                            <input type="text" name="writer"/>
                        </c:otherwise>
                    </c:choose>
                    <label>
                        <input type="checkbox"> <%-- 익명으로 게시글 등록 처리 --%>
                        익명
                    </label>
                </label>
            </td>
        </tr>
        <tr>
            <td>제목</td>
            <td><label>
                <input type="text" name="title"/>
            </label></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><label>
                <textarea name="content" cols="30" rows="10"></textarea>
            </label></td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td></td>
            <td><input id="postRegisterBtn" type="button" value="등록"/></td>
        </tr>
        </tfoot>
    </table>
</form>
</body>
</html>

