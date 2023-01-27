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
        <div class="container-fluid g-2">
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
                <div class="d-flex flex-column col-4 border border-dark" id="boardList">
                    <label class="h5 fw-semibold">
                        게시판 목록
                    </label>
                    <button type="button" class="btn btn-link board-title">전체 게시물</button>
                    <ul>
                    </ul>
                </div>
                <div class="d-flex gap-2 flex-column w-100 border border-info" id="postsByBoard">
                    <div class="d-flex gap-3">
                        <label class="h5 fw-semibold">
                        </label>
                        <span id="postCount"></span>
                    </div>
                    <div class="d-flex justify-content-end gap-2 visually-hidden" id="boardControl">
                        <button type='button' class='btn btn-link text-dark'>게시판 정보</button>
                        <button type='button' class='btn btn-link text-warning'>게시판 수정</button>
                        <button type='button' class='btn btn-link text-danger'>게시판 삭제</button>
                    </div>
                    <ul id="postListByBoard">
                    </ul>
                    <nav class="visually-hidden">
                        <ul class="pagination">
                            <li class="page-item">
                                <button class="page-link disabled">Previous</button>
                            </li>
                            <li class="page-item">
                                <button class="page-link">Next</button>
                            </li>
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
