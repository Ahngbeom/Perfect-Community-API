<html>
    <body>
        <div class="container-fluid g-2">
            <div class="d-flex gap-2 m-3">
                <div class="d-flex flex-column col-3 border border-dark gap-3" id="accountPreferences">
                    <div class="d-flex flex-wrap justify-content-between">
                        <label class="h5">
                            계정 관리
                        </label>
                    </div>
                    <div>
                        <ul>
                            <li>
                                <button type="button" class="btn btn-link" id="shoUserInfoFormBtn">계정 정보</button>
                            </li>
                            <li>
                                <button type="button" class="btn btn-link" id="showUserUpdateFormBtn">계정 정보 수정</button>
                            </li>
                            <li>
                                <button type="button" class="btn btn-link" id="showUserPasswordChangeFormBtn">비밀번호 변경
                                </button>
                            </li>
                            <li>
                                <button type="button" class="btn btn-link" id="showUserSecessionFormBtn">계정 탈퇴</button>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="d-flex flex-column col-9" id="mainContents">
                    <div class="border border-success p-3 gap-2">
                        <div id="userForm">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control-plaintext" id="userId">
                                <label for="userId">ID</label>
                            </div>
                            <div class="form-floating">
                                <input type="text" class="form-control-plaintext" id="nickname">
                                <label for="nickname">Nickname</label>
                            </div>
                            <div id="authorities">
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
                            <div class="form-check form-switch">
                                <label class="form-check-label" for="enabled">Enabled</label>
                                <input class="form-check-input" type="checkbox" role="switch" id="enabled">
                            </div>
                            <button type='button' class="btn btn-warning visually-hidden" id="userUpdateBtn">변경 사항 적용
                            </button>
                        </div>
                        <div class="d-flex justify-content-center visually-hidden" id="userPasswordVerifyForm">
                            <div class="col-6">
                                <div class="form-floating">
                                    <input type="password" class="form-control" id="password"/>
                                    <label for="password">Password</label>
                                </div>
                                <div class="d-flex justify-content-center mt-3">
                                    <button type="button" class="btn btn-primary mb-3">Confirm identity</button>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex flex-column gap-3 visually-hidden" id="userPasswordChangeForm">
                            <div class="d-flex justify-content-center">
                                <div class="form-floating col-6">
                                    <input type="password" class="form-control" id="newPassword"/>
                                    <label for="newPassword">새로운 비밀번호</label>
                                </div>
                            </div>
                            <div class="d-flex justify-content-center">
                                <div class="form-floating col-6">
                                    <input type="password" class="form-control" id="newPasswordReconfirm"/>
                                    <label for="newPasswordReconfirm">새로운 비밀번호 확인</label>
                                </div>
                            </div>
                            <div class="d-flex justify-content-center">
                                <button type="button" class="btn btn-info col-4 mb-3">Confirm</button>
                            </div>
                        </div>
                        <div class="d-flex flex-column visually-hidden" id="userSecessionConfirm">
                            <div class="h4 text-center">계정을 탈퇴하시겠습니까?</div>
                            <div class="d-flex justify-content-center">
                                <button type="button" class="btn btn-danger col-4 mb-3">Confirm</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/user/user.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/user/list.js"></script>
<script type="module">
    import {clearCookie} from "../../resources/js/pageCookie.js";

    clearCookie(POST_FILTER_OPTIONS_COOKIE_NAME);
    clearCookie(POST_DETAILS_COOKIE_NAME);
    clearCookie(PAGINATION_DATA_COOKIE_NAME);

    putUserInfo();
</script>