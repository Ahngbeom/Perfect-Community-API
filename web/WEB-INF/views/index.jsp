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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"
                integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA=="
                crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <title>Title</title>
    </head>
    <body>
        <div style="display: flex;">
            <div id="authentication">
            </div>
            <button type="button" id="logoutBtn" style="visibility: hidden">Logout</button>
        </div>
        <div id="loginForm">
            <label>
                <input type="text" name="username">
            </label>
            <label>
                <input type="password" name="password">
            </label>
            <button type="button" id="loginBtn">Login</button>
        </div>
        <div>
            <button type="button" id="reissueTokenBtn">Reissue token</button>
        </div>
        <div>
            <a href="/api/post/1">/api/post/1</a>
        </div>
    </body>
</html>
<script type="application/javascript">

    let username = undefined;
    let accessToken = undefined;

    $(document).ajaxSend((event, jqXHR, ajaxOptions) => {
        console.log("Access Token", accessToken);
        if (accessToken !== undefined && accessToken !== null && accessToken !== "")
            jqXHR.setRequestHeader("Authorization", "Bearer " + accessToken);
    });

    $(document).ajaxComplete((event, jqXHR, ajaxOptions) => {
        console.log(event);
        console.log(jqXHR);
        console.log(ajaxOptions);
        if (jqXHR.status >= 400 && jqXHR.status < 500) {
            alert(jqXHR.responseText);
        }
    });

    function loadAuthentication() {
        if (username !== undefined && accessToken !== undefined) {
            $("#authentication").html("Authenticated (" + username + ")").css("color", "green");
            $("#loginForm").css("visibility", "hidden");
            $("#logoutBtn").css("visibility", "visible");
        } else {
            $("#authentication").html("Not Authenticated").css("color", "red");
            $("#loginForm").css("visibility", "visible");
            $("#logoutBtn").css("visibility", "hidden");
        }
    }

    window.onload = () => loadAuthentication();

    $("#loginBtn").on('click', () => {
        $.ajax({
            type: 'post',
            url: '/api/login',
            async: false,
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                username: $("input[name='username']").val(),
                password: $("input[name='password']").val()
            }),
            success: (data, status, xhr) => {
                username = data.username;
                accessToken = xhr.getResponseHeader("Authorization").substring("Bearer ".length);
                loadAuthentication();
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
            async: false,
            contentType: 'application/json'
        }).done((data) => {
            console.log(data);
            accessToken = undefined;
        }).fail((xhr) => {
            console.error(xhr);
            console.error(xhr.responseText);
        }).always((jqXHR) => {
            loadAuthentication();
        });
    });

    $("#reissueTokenBtn").on('click', () => {
        const password = prompt("Password");
        $.ajax({
            type: 'post',
            url: '/api/jwt/reissue',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify({
                password: password
            }),
            success: (data) => {
                console.log(data);
                loadAuthentication();
            },
            error: (xhr) => {
                console.error(xhr);
            }
        })

    });
</script>
