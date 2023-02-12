<html>
    <body>
        <div class="container-fluid g-2">
            <div class="d-flex justify-content-center gap-2 m-3">
                <h2>Sign-up</h2>
            </div>
            <div class="d-flex justify-content-center gap-2 m-3">
                <div class="mb-3 row col-7">
                    <label for="inputUserId" class="col-sm-2 col-form-label">ID</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputUserId" required>
                    </div>
                    <div class="valid-feedback text-end">
                        사용 가능한 ID입니다.
                    </div>
                    <div class="invalid-feedback text-end">
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center gap-2 m-3">
                <div class="mb-3 row col-7">
                    <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword">
                    </div>
                    <div class="invalid-feedback text-end">
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center gap-2 m-3">
                <div class="mb-3 row col-7">
                    <label for="inputNickname" class="col-sm-2 col-form-label">Nickname</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputNickname">
                    </div>
                    <div class="valid-feedback text-end">
                        사용 가능한 Nickname입니다.
                    </div>
                    <div class="invalid-feedback text-end">
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center gap-2 m-3">
                <div class="mb-3 row col-7">
                    <label class="col-sm-2 col-form-label">Authorities</label>
                    <div class="col-sm-10" id="checkAuthorities">
                        <div class="form-check form-check-inline">
                            <label class="form-check-label">ROLE_ADMIN
                                <input class="form-check-input" type="checkbox" value="ROLE_ADMIN">
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <label class="form-check-label">ROLE_MANAGER
                                <input class="form-check-input" type="checkbox" value="ROLE_MANAGER">
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <label class="form-check-label">ROLE_USER
                                <input class="form-check-input" type="checkbox" value="ROLE_USER">
                            </label>
                        </div>
                    </div>
                    <div class="invalid-feedback text-end">
                        최소 1개 이상의 권한을 선택해야합니다.
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center gap-2">
                <button class="btn btn-info" id="signUpBtn">회원 가입</button>
            </div>
        </div>
    </body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/user/sign-up.js"></script>
<script type="module">
    import {clearCookie} from "../../resources/js/pageCookie.js";

    clearCookie(POST_FILTER_OPTIONS_COOKIE_NAME);
    clearCookie(POST_DETAILS_COOKIE_NAME);
    clearCookie(PAGINATION_DATA_COOKIE_NAME);
</script>