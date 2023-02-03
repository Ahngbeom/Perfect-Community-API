<%--
  ~ Copyright (C) 23. 2. 3. 오후 6:38 Ahngbeom (https://github.com/Ahngbeom)
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

<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <div class="container-fluid g-2">
            <div class="d-flex justify-content-between">
                <div class="col" id="currentTime">
                </div>
                <div class="col text-end">
                    <div>
                        <div class="d-flex justify-content-end align-items-center gap-2" id="loginForm">
                            <label>
                                <input type="text" name="username" class="form-control form-control-sm"
                                       placeholder="ID">
                            </label>
                            <label>
                                <input type="password" name="password" class="form-control form-control-sm"
                                       placeholder="Password">
                            </label>
                            <button type="button" id="loginBtn" class="btn btn-sm btn-outline-secondary">Login</button>
                        </div>
                        <div id="authentication" class="d-flex justify-content-end gap-2 visually-hidden">
                            <%--                            <div id="jwtInfo">--%>
                            <%--                                <div id="accessTokenValidity">--%>
                            <%--                                    <label>--%>
                            <%--                                        Access Token:--%>
                            <%--                                    </label>--%>
                            <%--                                    <span id="accessTokenValidityTimeLeft"></span>--%>
                            <%--                                </div>--%>
                            <%--                                <div id="refreshTokenValidity">--%>
                            <%--                                    <label>--%>
                            <%--                                        Refresh Token:--%>
                            <%--                                    </label>--%>
                            <%--                                    <span id="refreshTokenValidityTimeLeft"></span>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <div>
                                <span id="authenticatedUsername"></span>
                                <button type="button" id="logoutBtn" class="btn btn-sm btn-warning">Logout</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div class="d-flex gap-2 m-3">
                <div class="d-flex flex-column col-3 border border-dark" id="boardList">
                    <label class="h5">
                        게시판
                    </label>
                    <button type="button" class="btn btn-sm btn-link board-title">전체 게시물</button>
                    <ul>
                    </ul>
                </div>
                <div class="d-flex flex-column col-9 gap-2" id="mainContents">
                    <div id="postDetails" class="border border-info visually-hidden">
                        <div class="d-flex justify-content-between">
                            <span id="postTitle" class="h3"></span>
                            <button type="button" class="btn-close" aria-label="Close"></button>
                        </div>
                        <div class='text-break' id="postContents">
                        </div>
                    </div>
                    <div class="border border-success">
                        <div id="postsByBoard">
                            <label class="h4">
                                게시물
                            </label>
                            <span id="postCount"></span>
                            <ul id="postList">
                            </ul>
                            <nav class="d-flex justify-content-center visually-hidden" id="paginationNav">
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

            </div>
        </div>
    </body>
</html>
