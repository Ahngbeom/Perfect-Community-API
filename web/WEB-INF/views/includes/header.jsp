<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        <title>Perfect Community</title>

        <script src="${pageContext.request.contextPath}/resources/js/global_variables.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/resources/js/onload.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/resources/js/pageCookie.js"></script>
        <script type="module"
                src="${pageContext.request.contextPath}/resources/js/authentication/authentication.js"></script>
    </head>
    <body>
        <div class="container-fluid g-2">
            <div class="d-flex justify-content-between">
                <div class="col text-end">
                    <div id="authentication" class="d-flex justify-content-end gap-2">
                        <%-- When user is authenticated --%>
                        <div id="isAuthenticated" class="visually-hidden">
                            <span id="authenticatedUsername"></span>
                            <button type="button" id="logoutBtn" class="btn btn-sm btn-warning">Logout</button>
                        </div>

                        <%-- When user is not authenticated --%>
                        <div class="d-flex justify-content-end align-items-center gap-2" id="isAnonymous">
                            <label>
                                <input type="text" name="username" class="form-control form-control-sm"
                                       placeholder="ID">
                            </label>
                            <label>
                                <input type="password" name="password" class="form-control form-control-sm"
                                       placeholder="Password">
                            </label>
                            <button type="button" id="loginBtn" class="btn btn-sm btn-outline-secondary">Login
                            </button>
                            <button type='button' class='btn btn-sm btn-outline-secondary'>회원 가입</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-between gap-1 border border-light visually-hidden"
                 id="additionalButtons">
                <div>
                    <button type='button' class='btn btn-sm btn-outline-secondary' onclick="location.href='/'">메인 페이지
                    </button>
                </div>
                <div>
                    <button type='button' class='btn btn-sm btn-outline-secondary' id='scrapedPosts'>게시물 스크랩 목록</button>
                    <button type='button' class='btn btn-sm btn-outline-secondary' id='userListBtn'>유저 목록</button>
                    <button type='button' class='btn btn-sm btn-outline-secondary' id='accountPreferencesBtn'
                            onclick="location.href='/user'">계정 관리
                    </button>
                </div>
            </div>
        </div>
    </body>
</html>
