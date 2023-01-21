<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2023-01-18
  Time: 오후 4:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <title>Title</title>
    </head>
    <body>
        <div id="authentication" style="color: red;">
            Not Authenticated
        </div>
        <input type="text" name="username">
        <input type="password" name="password">
        <button id="loginBtn">Login</button>
        <div style="border: black"></div>
    </body>
</html>
<script type="application/javascript">
    $("#loginBtn").on('click', () => {
       $.ajax({
           type: 'post',
           url: '/api/login',
           contentType: 'application/json',
           dataType: 'json',
           data: JSON.stringify({
               username: $("input[name='username']").val(),
               password: $("input[name='password']").val()
           }),
           success: (data, status, xhr) => {
               // alert(data);
               $("#authentication").html("Authenticated").css("color", "green")
               console.log("Data: ", data);
               console.log("Status: ", status);
               console.log("XHR: ", xhr);
               console.log(xhr.getResponseHeader("Authorization"));
           },
           error: (xhr) => {
               alert(xhr.responseText);
           }
       })
    });
</script>
