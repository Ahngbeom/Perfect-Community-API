<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-03-19
  Time: 오후 1:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board - ${post.title}</title>
</head>
<body>
<h1>Board Post</h1>
<table>
    <tbody>
    <tr>
        <td>작성자</td>
        <td><input type="text" value="${post.writer}" readonly/></td>
    </tr>
    <tr>
        <td>제목</td>
        <td><input type="text" value="${post.title}" readonly/></td>
    </tr>
    <tr>
        <td>내용</td>
        <td><textarea name="content" cols="30" rows="10" readonly>${post.content}</textarea></td>
    </tr>
    </tbody>
    <tfoot>
    <tr>
        <td>
            <button onclick="location.href='/board/list'">목록</button>
        </td>
        <td align="right">
            <button onclick="location.href='/board/modify?bno=${post.bno}'">수정</button>
            <form action="/board/remove" method="post">
                <input type="hidden" name="bno" value="${post.bno}">
                <input type="submit" value="삭제"/>
            </form>
        </td>
    </tr>
    </tfoot>
</table>
</body>
<script type="text/javascript">

</script>
</html>
