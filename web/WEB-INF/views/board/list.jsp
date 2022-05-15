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
<body>
<h1>Board</h1>

<div class="container-fluid">
    <div class="justify-content-between">
        <form method="get" action="${pageContext.request.contextPath}/board/search">
            <label>
                <input type="search" name="keyword"/>
            </label>
            <input type="submit" value="검색"/>
        </form>
        <button onclick="location.href='/board/register'">게시물 작성</button>
        <table>
            <thead>
            <tr>
                <th>번호</th>
                <th style="width: 200px">제목</th>
                <th>작성자</th>
                <th>업로드 날짜</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="post" items="${BoardList}" varStatus="status">
                <tr>
                    <td><c:out value="${post.bno}"/></td>
                    <td><a href="${pageContext.request.contextPath}/board/post?bno=${post.bno}"><c:out
                            value="${post.title}"/></a></td>
                    <td><c:out value="${post.writer}"/></td>

                    <td>${post.dateToToday}</td>
<%--                    <fmt:parseDate value="${post.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedRegDate"--%>
<%--                                   type="both"/>--%>
<%--                    <td><fmt:formatDate value="${parsedRegDate}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/></td>--%>
<%--                    <fmt:parseDate value="${post.updateDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedUpdateDate"--%>
<%--                                   type="both"/>--%>
<%--                    <td><fmt:formatDate value="${parsedUpdateDate}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/></td>--%>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td id="pageList" colspan="5">
                    <c:forEach var="page" begin="1" end="${pageAmount}" varStatus="status">
                        <%--                <a href="/board/list?page=${page}">${page}</a>--%>
                        <a href="${pageContext.request.contextPath}?keyword=${param.keyword}&page=${page}">${page}</a>
                    </c:forEach>
                    <%-- 유효하지않은 page 번호를 담아 url 요청할 경우 예외 처리 필요 --%>
                    <%-- page가 많을 경우 10개 단위로 페이지의 페이지 구현 (Prev, 1, 2.....10, Next) --%>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
    <div>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <form method="post" action="${pageContext.request.contextPath}/board/removeAll">
                <button>모든 게시물 삭제</button> <!-- 관리자 권한을 가지고있어야 함 -->
            </form>

            <form method="post" action="${pageContext.request.contextPath}/board/createDummy">
                <label>
                    개수 :
                    <select name="dummyAmount">
                        <option value="1">1</option>
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                    </select>
                </label>
                <button>게시물 더미 생성</button> <!-- 관리자 권한을 가지고있어야 함 -->
            </form>
        </sec:authorize>
    </div>
</div>


</body>
</html>

