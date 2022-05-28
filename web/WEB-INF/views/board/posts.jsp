<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-03-19
  Time: 오후 1:24
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
</head>
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
                        <input type="text" name="writer" value="${Posts.writer}" class="form-control"
                               placeholder="Writer" readonly/>
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
<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', () => {
        const postsForm = $("#postsForm");
        const postsDeleteBtn = postsForm.find("#postsDeleteBtn");
        const postsModifyBtn = postsForm.find("#postsModifyBtn");
        const postsWriterInput = postsForm.find("input[name='writer']");
        const postsTitleInput = postsForm.find("input[name='title']");
        const postsContentInput = postsForm.find("textarea[name='content']");

        postsDeleteBtn[0].addEventListener('click', () => {
            if (${!isAdmin}) {
                $.ajax({
                    url: "/rest/board/passwordCheck",
                    type: "POST",
                    data: {bno: postsForm.find("input[name='bno']")[0].value},
                    success: function (data) {
                        if (data) {
                            if (${!isAuthenticated} &&
                            ${empty isAdmin or !isAdmin})
                            {
                                $("#centerModal").find("#centerModalTitle").text("비밀번호 확인");
                                $("#centerModal").find(".modal-body").html("<input type='password' name='password' placeholder='Password' class='form-control'/><p></p>");
                                $("#centerModal").find("#centerModalSubmit").text("확인");
                                $("#centerModal").modal();

                                $("#centerModal").find("#centerModalSubmit")[0].addEventListener('click', () => {
                                    $.ajax({
                                        url: "/rest/board/passwordMatches",
                                        type: "POST",
                                        data: {
                                            bno: postsForm.find("input[name='bno']")[0].value,
                                            password: document.querySelector(".modal-body input[name='password']").value
                                        },
                                        success: function (data) {
                                            if (data) {
                                                postsForm.attr("method", "POST");
                                                postsForm.attr("action", "/board/remove");
                                                postsForm.submit();
                                            } else {
                                                putMessageForInputTag("danger", $("#centerModal").find(".modal-body input[name='password']")[0], "비밀번호가 일치하지않습니다.");
                                            }
                                        }
                                    });

                                });
                            }
                        } else {
                            putServerAlert("로그인 후 진행해주세요.");
                        }
                    }
                });
            } else {
                postsForm.attr("method", "POST");
                postsForm.attr("action", "/board/remove");
                postsForm.submit();
            }
        });

        postsModifyBtn[0].addEventListener('click', () => {
            if (${not empty isAdmin}) {
                postsWriterInput.removeAttr('readonly');
                postsTitleInput.removeAttr('readonly');
                postsContentInput.removeAttr('readonly');
            } else {
                putServerAlert("게시물 수정 권한이 없습니다.");
            }
        });
    });
</script>