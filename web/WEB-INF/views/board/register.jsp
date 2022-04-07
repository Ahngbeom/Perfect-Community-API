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
<h2 id="serverMsg"></h2>
<form method="post" action="${pageContext.request.contextPath}/board/register">
    <table>
        <thead>

        </thead>
        <tbody>
        <tr>
            <td>작성자</td>
            <td><label>
                <input type="text" name="writer" value=""/>
            </label></td>
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
<script type="text/javascript">

    document.addEventListener('DOMContentLoaded', () => {
        let formTag = document.querySelector('form');
        // let registerBtn = document.getElementById("postRegisterBtn");
        let registerBtn = formTag.querySelector('#postRegisterBtn');
        registerBtn.addEventListener('click', () => {
            // console.log(formTag.querySelector("input[name='writer']"));
            // console.log(formTag.querySelector("input[name='title']"));
            // console.log(formTag.querySelector("textarea[name='content']"));
        });

        formTag.querySelector("input[name='writer']").addEventListener('input', (evt) => {
           console.log(evt.currentTarget.value);
        });
    });
</script>
</html>

