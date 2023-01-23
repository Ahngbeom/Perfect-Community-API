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
        <div id="logoutForm" style="visibility: hidden">
            <button id="logoutBtn">Logout</button>
        </div>
        <div id="loginForm">
            <label>
                <input type="text" name="username">
            </label>
            <label>
                <input type="password" name="password">
            </label>
            <button id="loginBtn">Login</button>
        </div>
        <div style="border: black">
            <a href="/api/post/1">/api/post/1</a>
        </div>
    </body>
</html>
<script type="application/javascript">
    window.onload = () => {
        // const username = localStorage.getItem("username");
        // if (username !== undefined && username !== null) {
        //     $("#authentication").html(username).css("color", "green");
        // }
        $.ajax({
            type: 'get',
            url: '/api/user',
            contentType: 'application/json',
            success: (data) => {
                console.log(data);
                username = data.userId;
                $("#authentication").html("Authenticated (" + username + ")").css("color", "green");
                $("#loginForm").css("visibility", "hidden");
                $("#logoutForm").css("visibility", "visible");
            },
            error: (xhr) => {
                console.error(xhr.responseText);
            }
        });
    }

    let username;
    let accessToken;

    // $.ajaxSetup({
    //     contentType: 'application/json',
    //     dataType: 'json'
    // });

    $(document).ajaxSend((event, jqXHR, ajaxOptions) => {
        if (accessToken !== undefined && accessToken !== null && accessToken !== "")
            jqXHR.setRequestHeader("Authorization", "Bearer " + accessToken);
    });

    // $(document).ajaxComplete((event, jqXHR, ajaxOptions) => {
    //     console.log(event);
    //     console.log(jqXHR);
    //     console.log(ajaxOptions);
    // });

    $("#loginBtn").on('click', () => {
        $.ajax({
            type: 'post',
            url: '/api/login',
            contentType: 'application/json',
            data: JSON.stringify({
                username: $("input[name='username']").val(),
                password: $("input[name='password']").val()
            }),
            success: (data, status, xhr) => {
                username = data.username;
                accessToken = xhr.getResponseHeader("Authorization").substring("Bearer ".length);
                console.log("Data: ", data);
                console.log("Status: ", status);
                console.log("XHR: ", xhr);
                console.log(accessToken);
                $("#authentication").html("Authenticated (" + username + ")").css("color", "green");
                $("#loginForm").css("visibility", "hidden");
                $("#logoutForm").css("visibility", "visible");
            },
            error: (xhr) => {
                alert(xhr.responseText);
            }
        })
    });

    $("#logoutBtn").on('click', () => {
       $.ajax({
           type: 'post',
           url: '/api/logout',
           contentType: 'application/json',
           success: (data) => {
               alert(data);
           }
       });
    });
</script>
