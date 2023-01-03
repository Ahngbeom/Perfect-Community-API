<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2023-01-03
  Time: 오후 2:40
  To change this template use File | Settings | File Templates.
--%>
<html>
    <body>
        <div class="container-fluid">
            <h1>Perfect Community</h1>
            <h2>API Docs</h2>
            <div class="d-flex justify-content-around">
                <div class="col-6">

                    <h5>로그인 & 로그아웃</h5>
                    <ul>
                        <li>
                            <a href="/login">/login</a>
                            <p>Spring Security에서 기본으로 제공해주는 로그인 페이지를 사용하고있습니다.</p>
                        </li>
                        <li>
                            <a methods="post" href="/logout">/logout</a>
                            <p>Default Spring Security Logout Process</p>
                            <p>Default Spring Security Logout Process</p>
                        </li>
                    </ul>
                    <h5>회원</h5>
                    <ul>
                        <li>
                            <a href="#" onclick="getAuthentication()">/api/authentication</a>
                            <p>Default Spring Security Login Page</p>
                        </li>
                    </ul>

                    <h5>게시판</h5>
                    <ul>
                        <li>

                        </li>
                    </ul>

                    <h5>게시물</h5>
                    <ul>
                        <li>

                        </li>
                    </ul>

                    <h5>이 외</h5>
                    <ul>
                        <li>
                            <button type="button" id="server-info-btn">서버 정보 조회</button>
                        </li>
                    </ul>
                </div>
                <div class="col-6 border border-black">
                    <h3>Response Body</h3>
                    <div id="api-response-area">

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
<script type="application/javascript">
    $(document).ready(() => {
        console.log("Perfect Community API");
        getAuthentication();
        serverState(true);
    });
</script>

