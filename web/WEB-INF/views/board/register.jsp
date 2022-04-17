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
</head>
<style>
    div {
        margin: 10px;
    }
</style>
<body>

<h1>Board Register</h1>
<h2 id="serverMsg" form-value-status="EMPTY"></h2>
<form method="post" action="${pageContext.request.contextPath}/board/register">
    <table>
        <thead>

        </thead>
        <tbody>
        <tr>
            <td>작성자</td>
            <td>
                <label>
                    <input type="text" name="writer" />
                </label>
            </td>
            <td class="message">

            </td>
        </tr>
        <tr>
            <td>제목</td>
            <td><label>
                <input type="text" name="title" />
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

