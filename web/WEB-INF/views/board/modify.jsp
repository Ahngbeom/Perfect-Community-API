<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-03-30
  Time: 오후 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Board Modify</h1>

<form method="post" action="${pageContext.request.contextPath}/board/modify">
    <input type="hidden" name="bno" value="${Post.bno}">
    <table>
        <thead>

        </thead>
        <tbody>
        <tr>
            <td>작성자</td>
            <td><input type="text" name="writer" value="${Post.writer}"/></td>
        </tr>
        <tr>
            <td>제목</td>
            <td><input type="text" name="title" value="${Post.title}"/></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><textarea name="content" cols="30" rows="10">${Post.content}</textarea></td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td></td>
            <td align="right"><input type="submit" value="수정"/></td>
        </tr>
        </tfoot>
    </table>
</form>
</body>
</html>
