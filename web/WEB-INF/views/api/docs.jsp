<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2023-01-03
  Time: 오후 2:40
  To change this template use File | Settings | File Templates.
--%>
<html>
    <body>
        <%--        <div class="container-fluid fixed-top mt-5 px-5" style="">--%>
        <%--            <div class="d-flex justify-content-center">--%>
        <%--                <div id="api-response-area" class="text-center py-3">--%>
        <%--                    asdad--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <div class="container-fluid" style="position: relative; top: 80px;">
            <div class="d-flex justify-content-between">
                <div class="col-2">
                    <a href="/docs" class="btn btn-link text-dark fs-2">API Docs</a>
                    <div id="simple-list-example"
                         class="d-flex flex-column gap-2 simple-list-example-scrollspy text-center">
                        <a class="p-1 rounded" href="#introduction">소개</a>
                        <a class="p-1 rounded" href="#simple-list-item-1">로그인 & 로그아웃</a>
                        <a class="p-1 rounded" href="#simple-list-item-2">회원</a>
                        <a class="p-1 rounded" href="#simple-list-item-3">게시판</a>
                        <a class="p-1 rounded" href="#simple-list-item-4">게시물</a>
                        <a class="p-1 rounded" href="#simple-list-item-5">이 외</a>
                    </div>
                </div>
                <div class="col-10">
                    <div data-bs-spy="scroll" data-bs-target="#simple-list-example" data-bs-offset="0"
                         data-bs-smooth-scroll="true" class="scrollspy-example" tabindex="0">
                        <h5 id="introduction">소개</h5>
                        <p>
                            완벽한 커뮤니티 웹 사이트 구축을 목표로 한 저의 사이드 프로젝트 API입니다.<br>
                            <b>Spring Framework</b> ver.5.3.22<br>
                            <b>Spring Security</b> ver.5.7.2<br>
                            <b>MariaDB</b> ver.10.8.3<br>
                            <b>MyBatis</b> ver.3.5.10<br>
                        </p>
                        <h5 id="simple-list-item-1">로그인 & 로그아웃</h5>
                        <ul>
                            <li>
                                <a href="/login">/login</a>
                                <p>Spring Security에서 기본으로 제공해주는 로그인 페이지를 사용하고 있습니다.</p>
                                <p>
                                    로그인 성공 및 실패에 대한 핸들러는 직접 재구현하여 사용하고 있습니다.<br>
                                    로그인 성공 시, "/" URI로 리디렉션됩니다.<br>
                                    로그인 실패 시, "/login" URI로 리디렉션되고, error 파라미터가 추가됩니다.<br>
                                </p>
                            </li>
                            <li>
                                <a methods="post" href="/logout">/logout</a>
                                <p>Spring Security에서 기본으로 제공해주는 로그아웃 프로세스를 사용하고 있습니다.</p>
                                <p>
                                    로그아웃 성공에 대한 핸들러는 직접 재구현하여 사용하고 있습니다.<br>
                                    로그아웃 처리가 성공했다면, 요청 헤더의 "Referer"의 값을 조회하여 이전 페이지로 리디렉션됩니다.<br>
                                    요청 헤더의 "Referer"의 값이 유효하지 않다면 "/"로 리디렉션됩니다.<br>
                                </p>
                            </li>
                        </ul>
                        <h5 id="simple-list-item-2">회원</h5>
                        <ul>
                            <li>
                                <button type="button" id="api-authentication-btn" class="btn btn-link">
                                    /api/authentication
                                </button>
                                <p>현재 클라이언트의 인증 정보를 조회하는 API입니다.</p>
                                <p>현재 클라이언트의 인증 정보가 유효하지 않다면, 즉 비로그인 상태라면 요청이 실패됩니다.</p>
                            </li>
                            <li>
                                <button type="button" id="api-user-info-btn" class="btn btn-link">
                                    /api/user/{userId} - [GET]
                                </button>
                                <p>현재 클라이언트의 정보를 조회하는 API입니다.</p>
                                <p>
                                    이 API는 요청 주소에 유저의 아이디가 필수적으로 포함되어야합니다.<br>
                                    따라서, 클라이언트가 비로그인 상태라면 서버에 요청할 수 없습니다.
                                </p>
                            </li>
                            <li>
                                <button type="button" id="api-user-list-btn" class="btn btn-link">
                                    /api/user - [GET]
                                </button>
                                <p>이 사이트에 등록된 회원 목록을 조회하는 API입니다.</p>
                                <p>
                                    이 API는 권한 제한이 없습니다.<br>
                                    비로그인 유저 또한 접근할 수 있습니다.
                                </p>
                            </li>
                            <li>
                                <button type="button" id="api-user-signup-btn" class="btn btn-link">
                                    /api/user - [POST]
                                </button>
                                <form id="api-user-signup-form">
                                    <label>아이디</label>
                                    <input type="text" name="userId" class="form-control col-4"/>
                                    <label>패스워드</label>
                                    <input type="password" name="password" class="form-control col-4"/>
                                    <label>닉네임</label>
                                    <input type="text" name="nickname" class="form-control col-4"/>
                                </form>
                                <p>이 사이트에 회원 가입을 할 수 있는 API입니다.</p>
                                <p>
                                    이 API는 비로그인 상태에서만 접근할 수 있습니다.<br>
                                    현재 계정 정책은 다음과 같다.
                                </p>
                                <ul>
                                    <li>아이디는 5자 이상이어야합니다.</li>
                                    <li>패스워드는 4자 이상이어야합니다.</li>
                                    <li>닉네임은는 2자 이상이어야합니다.</li>
                                    <li>아이디와 닉네임은 중복 검증을 수행합니다.</li>
                                </ul>
                            </li>

                        </ul>

                        <h5 id="simple-list-item-3">게시판</h5>
                        <ul>
                            <li>

                            </li>
                        </ul>

                        <h5 id="simple-list-item-4">게시물</h5>
                        <ul>
                            <li>

                            </li>
                        </ul>

                        <h5 id="simple-list-item-5">이 외</h5>
                        <ul>
                            <li>
                                <button type="button" id="server-info-btn">서버 정보 조회</button>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>


