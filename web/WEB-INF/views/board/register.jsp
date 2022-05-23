<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-03-30
  Time: 오후 10:37
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
</head>
<body>
<div class="container-fluid w-100">
    <div class="d-flex justify-content-center">
        <form method="post" action="/board/register" class="w-75" id="boardForm">
            <div class="form-group">
                <label>작성자</label>
                <div class="d-flex justify-content-between w-100">
                    <c:choose>
                        <c:when test="${isAuthenticated}">
                            <sec:authentication property="principal.member" var="memberInfo"/>
                            <div class="w-75">
                                <input type="text" name="writer" value="${memberInfo.userName}" class="form-control"
                                       placeholder="Writer" readonly/>
                                <p></p>
                            </div>
                            <div class="form-check w-25 text-center">
                                <input class="form-check-input" type="checkbox" id="boardRegisterFormWriterIsAnonymous">
                                <label class="form-check-label" for="boardRegisterFormWriterIsAnonymous">
                                    익명
                                </label>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="w-100">
                                <input type="text" name="writer" class="form-control"
                                       placeholder="Writer"/>
                                <p></p>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="form-group">
                <label for="boardRegisterFormTitle">제목</label>
                <div class="w-100">
                    <input type="text" name="title" class="form-control"
                           placeholder="Title" id="boardRegisterFormTitle"/>
                    <p></p>
                </div>
            </div>
            <div class="form-group">
                <label for="boardRegisterFormContent">내용</label>
                <textarea class="form-control" name="content" id="boardRegisterFormContent" rows="5"
                          placeholder="Please enter your content"></textarea>
                <p></p>
            </div>
            <input id="postRegisterBtn" type="button" class="btn btn-secondary float-right w-25" value="등록"/>
        </form>
    </div>
</div>
</body>
</html>

