<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-03-30
  Time: 오후 10:37
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Board Modify - ${Post.title}</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board/boardForm.js"></script>
</head>
<body>
<h1>Board Modify</h1>
<h2 id="serverMsg"></h2>
<form action="${pageContext.request.contextPath}/board/modify" method="post">
    <input type="hidden" name="bno" value="${Post.bno}">
    <table>
        <thead>

        </thead>
        <tbody>
        <tr>
            <td>작성자</td>
            <td>
                <label>
                    <input type="text" name="writer" value="${Post.writer}"/>
                </label>
            </td>
        </tr>
        <tr>
            <td>제목</td>
            <td>
                <label>
                    <input type="text" name="title" value="${Post.title}"/>
                </label>
            </td>
        </tr>
        <tr>
            <td>내용</td>
            <td>
                <label>
                    <textarea name="content" cols="30" rows="10">${Post.contents}</textarea>
                </label>
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td></td>
            <td><input id="postRegisterBtn" type="submit" value="수정"/></td>
        </tr>
        </tfoot>
    </table>
</form>
</body>
</html>
