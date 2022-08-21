<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-03-19
  Time: 오후 1:24
  To change this template use File | Settings | File Templates.
--%>
<html>
<body>
<div class="container-fluid">
    <div class="d-flex justify-content-center">
        <form class="w-75" id="postsForm">
            <%--            <sec:authorize access="hasRole('ROLE_ADMIN')" var="">--%>
            <%--            </sec:authorize>--%>
            <input type="hidden" name="bno" value="${Posts.bno}">
            <div class="form-group">
                <label>작성자</label>
                <div class="d-flex justify-content-between w-100">
                    <div class="w-100">
                        <label>
                            <input type="text" name="writer" value="${Posts.writer}" class="form-control"
                                   placeholder="Writer" readonly/>
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="boardRegisterFormTitle">제목</label>
                <div class="w-100">
                    <input type="text" name="title" class="form-control"
                           placeholder="Title" id="boardRegisterFormTitle" value="${Posts.title}" readonly/>
                    <p></p>
                </div>
            </div>
            <div class="form-group">
                <label for="boardRegisterFormContent">내용</label>
                <textarea class="form-control" name="content" id="boardRegisterFormContent" rows="5"
                          placeholder="Please enter your content" readonly>${Posts.content}</textarea>
                <p></p>
            </div>
            <button type="button" class="btn btn-secondary" onclick="location.href='/board/list'">목록</button>
            <button type="button" id="postsModifyBtn" class="btn btn-warning float-right w-25">수정</button>
            <input type="button" value="삭제" id="postsDeleteBtn" class="btn btn-danger float-right w-25"/>
        </form>

        <form method="post" action="${pageContext.request.contextPath}/board/remove" id="postsDeleteForm">
            <input type="hidden" name="bno" value="${Posts.bno}">
        </form>
    </div>
</div>
</body>
</html>
