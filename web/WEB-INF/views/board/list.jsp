<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-03-19
  Time: 오후 1:24
  To change this template use File | Settings | File Templates.
--%>
<html>
<body>
<div class="container-fluid w-100">
    <div class="d-flex justify-content-between w-100">
        <div class="w-100">
            <nav class="navbar navbar-light bg-light justify-content-between">
                <form method="get" action="${pageContext.request.contextPath}/board/search"
                      class="form-inline">
                    <div class="mr-lg-3">
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" name="checkTitle" class="custom-control-input" id="checkTitle"
                                   checked>
                            <label class="custom-control-label" for="checkTitle">제목</label>
                        </div>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" name="checkContent" class="custom-control-input" id="checkContent">
                            <label class="custom-control-label" for="checkContent">내용</label>
                        </div>
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" name="checkWriter" class="custom-control-input" id="checkWriter">
                            <label class="custom-control-label" for="checkWriter">작성자</label>
                        </div>
                    </div>
                    <div class="form-inline">
                        <input class="form-control mr-sm-2" type="search" name="keyword" placeholder="Search"
                               aria-label="Search" required>
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </div>
                </form>
                <button type="button" onclick="location.href='/board/register'" class="btn btn-outline-secondary">게시물
                    작성
                </button>
            </nav>
            <table class="table table-striped">
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
                            <%--                                   boardAlertType="both"/>--%>
                            <%--                    <td><fmt:formatDate value="${parsedRegDate}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/></td>--%>
                            <%--                    <fmt:parseDate value="${post.updateDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedUpdateDate"--%>
                            <%--                                   boardAlertType="both"/>--%>
                            <%--                    <td><fmt:formatDate value="${parsedUpdateDate}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/></td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--            <c:choose>--%>
            <%--                <c:when test="${pageAmount gt 10 and pageAmount % 10 le 9}">--%>
            <%--                    <c:set var="pageMin" value="${(pageAmount - pageAmount % 10) + 1}"/>--%>
            <%--                </c:when>--%>
            <%--                <c:otherwise>--%>
            <%--                    <c:set var="pageMin" value="${pageAmount - 9}"/>--%>
            <%--                </c:otherwise>--%>
            <%--            </c:choose>--%>
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <c:if test="${pagination.pageMin - 1 gt 0}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/board/list?page=${pagination.pageMin - 1}">Previous</a>
                        </li>
                    </c:if>
                    <c:forEach var="page" begin="${pagination.pageMin}" end="${pagination.pageMax}" varStatus="status">
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}?keyword=${param.keyword}&page=${page}">${page}</a>
                        </li>
                    </c:forEach>
                    <%-- 유효하지않은 page 번호를 담아 url 요청할 경우 예외 처리 필요 --%>
                    <%-- page가 많을 경우 10개 단위로 페이지의 페이지 구현 (Prev, 1, 2.....10, Next) --%>

                    <c:if test="${pagination.pageMax + 1 lt pagination.pageAmount}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/board/list?page=${pagination.pageMax + 1}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="border border-dark rounded w-25">
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
            </div>
        </sec:authorize>
    </div>
</div>
</body>
</html>

