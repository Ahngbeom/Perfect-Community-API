<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2023-01-05
  Time: 오후 12:40
  To change this template use File | Settings | File Templates.
--%>
<div class="col-2 position-fixed">
    <a href="/docs" class="btn btn-link text-dark fs-2">API Docs</a>
    <div class="d-flex flex-column gap-2">
        <a class="p-1 rounded" href="/docs/intro">소개</a>
        <a class="p-1 rounded" href="/docs/credentials">로그인 & 로그아웃</a>
        <div class="collapse" id="docs-credentials-collapse">
            <ul>
                <li>
                    <button href="#login">로그인</button>
                </li>
                <li>
                    <button href="#logout">로그아웃</button>
                </li>
            </ul>
        </div>
        <a class="p-1 rounded" href="/docs/user">회원</a>
        <div class="collapse" id="docs-user-collapse">
            <ul>
                <li>
                    <button data-anchor-target="#authentication">User Authentication</button>
                </li>
                <li>
                    <button data-anchor-target="#user-info">User Info</button>
                </li>
                <li>
                    <button data-anchor-target="#user-list">User List</button>
                </li>
                <li>
                    <button data-anchor-target="#sign-up">Sign-Up</button>
                </li>
            </ul>
        </div>
        <a class="p-1 rounded" href="/docs/board">게시판</a>
        <a class="p-1 rounded" href="/docs/post">게시물</a>
        <a class="p-1 rounded" href="/docs/others">이 외</a>
    </div>
</div>
<script type="application/javascript">

    $(document).ready(() => {

    });


</script>
