<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-03-30
  Time: 오후 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Board - Register</title>
</head>
<style>
    div {
        margin: 10px;
    }
</style>
<body>

<h1>Board Register</h1>

<form method="post" action="${pageContext.request.contextPath}/board/register">
    <table>
        <thead>

        </thead>
        <tbody>
        <tr>
            <td>작성자</td>
            <td><input type="text" name="writer"/></td>
        </tr>
        <tr>
            <td>제목</td>
            <td><input type="text" name="title"/></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><textarea name="content" cols="30" rows="10"></textarea></td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td></td>
            <td align="right"><input type="submit" value="등록"/></td>
        </tr>
        </tfoot>
    </table>
</form>
</body>
</html>
