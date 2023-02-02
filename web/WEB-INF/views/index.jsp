<%--
  ~ Copyright (C) 2023 Ahngbeom (bbu0704@gmail.com)
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~      http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
                integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
                integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
                crossorigin="anonymous"></script>
        <title>Title</title>
    </head>
    <body>
        <div class="container g-2">
            <div class="d-flex">
                <div class="col-6">
                    <div id="authentication">
                        <span></span>
                        <button type="button" id="logoutBtn" class="btn btn-warning visually-hidden">Logout</button>
                    </div>
                    <div id="jwtInfo">
                        <div id="accessTokenValidity">
                            <label>
                                Access Token:
                            </label>
                            <span id="accessTokenValidityTimeLeft"></span>
                        </div>
                        <div id="refreshTokenValidity">
                            <label>
                                Refresh Token:
                            </label>
                            <span id="refreshTokenValidityTimeLeft"></span>
                        </div>
                    </div>
                </div>
                <div class="col-6 text-end" id="currentTime">
                </div>
            </div>
            <div class="col-12" id="loginForm">
                <label>
                    <input type="text" name="username">
                </label>
                <label>
                    <input type="password" name="password">
                </label>
                <button type="button" id="loginBtn">Login</button>
            </div>
            <div class="d-flex gap-2">
                <div class="d-flex flex-column border border-dark" id="boardList">
                    <label class="h5">
                        게시물 목록
                    </label>
                    <button type="button" class="btn btn-sm btn-link board-title">전체 게시물</button>
                    <ul>
                    </ul>
                </div>
                <div class="d-flex flex-column border border-info">
                    <label class="h5">
                        게시물 목록
                    </label>
                    <ul id="postListByBoard">
                    </ul>
                    <nav class="visually-hidden">
                        <ul class="pagination">
                            <li class="page-item"><button class="page-link disabled">Previous</button></li>
                            <li class="page-item"><button class="page-link">Next</button></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jwt.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/authentication.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/board.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/post.js"></script>
<script type="application/javascript">

    setInterval(() => {
        $("#currentTime").html(new Date());
    }, 1000);

    $(".api-link").on('click', (e) => {
        const api = $(e.target);
        $.ajax({
            type: api.data('api-method'),
            url: api.html(),
            contentType: 'application/json',
            dataType: 'json'
        }).done((data) => {
            $("#api-result").addClass("text-wrap").text(JSON.stringify(data));
        }).fail((xhr) => {
            $("#api-result").html(xhr.responseText);
        });
    })
</script>
