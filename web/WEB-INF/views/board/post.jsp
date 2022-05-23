<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-03-19
  Time: 오후 1:24
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Board - ${Post.title}</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board/boardForm.js"></script>
</head>
<body>
<h1>Board Post</h1>
<h2 id="serverMsg"></h2>
<table>
    <tbody>
    <tr>
        <td>작성자</td>
        <td>
            <label>
                <input type="text" value="${Post.writer}" readonly/>
            </label>
        </td>
    </tr>
    <tr>
        <td>제목</td>
        <td><label>
            <input type="text" value="${Post.title}" readonly/>
        </label></td>
    </tr>
    <tr>
        <td>내용</td>
        <td>
            <label>
                <textarea name="content" cols="30" rows="10" readonly>${Post.content}</textarea>
            </label>
        </td>
    </tr>
    </tbody>
    <tfoot>
    <tr>
        <td>
            <button onclick="location.href='/board/list'">목록</button>
        </td>
        <td>
            <button onclick="location.href='/board/modify?bno=${Post.bno}'">수정</button>
            <form method="post" action="${pageContext.request.contextPath}/board/remove">
                <input type="hidden" name="bno" value="${Post.bno}">
                <input type="submit" value="삭제"/>
            </form>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>