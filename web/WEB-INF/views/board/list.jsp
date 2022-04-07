<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-03-19
  Time: 오후 1:24
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ include file="../includes/header.jsp" %>--%>
<html>
<head>
    <title>Board</title>
</head>
<style>
    table, td {
        border-style: solid;
        table-layout: auto;
    }
</style>
<body>
<h1>Board</h1>
<h2 id="serverMsg"></h2>

<form method="post" action="${pageContext.request.contextPath}/board/removeAll">
    <button>모든 게시물 삭제</button> <!-- 관리자 권한을 가지고있어야 함 -->
</form>

<button onclick="location.href='/board/register'">게시물 작성</button>

<table>
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일자</th>
        <th>수정일자</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="post" items="${BoardList}" varStatus="status">
        <tr>
            <td><c:out value="${post.bno}"/></td>
            <td><a href="${pageContext.request.contextPath}/board/post?bno=${post.bno}"><c:out value="${post.title}"/></a></td>
            <td><c:out value="${post.writer}"/></td>
            <fmt:parseDate value="${post.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedRegDate" type="both" />
            <td><fmt:formatDate value="${parsedRegDate}" pattern="yyyy년 MM월 dd일" /></td>
            <fmt:parseDate value="${post.updateDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedUpdateDate" type="both" />
            <td><fmt:formatDate value="${parsedUpdateDate}" pattern="yyyy년 MM월 dd일" /></td>
<%--     게시글이 등록된지 하루가 지나지않았을 경우 날짜 대신 "몇분 전", "몇시간 전" 처리하기       --%>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
