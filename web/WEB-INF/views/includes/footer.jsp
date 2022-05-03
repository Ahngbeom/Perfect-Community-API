<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-04-07
  Time: 오전 12:47
  To change this template use File | Settings | File Templates.
--%>
<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', () => {
        let serverMessage = '<c:out value="${serverMessage}" />';
        let processType = '<c:out value="${type}" />';
        let processState = '<c:out value="${state}" />';
        let serverMsgTag = document.querySelector('#serverMessage');

        // console.log('<c:out value="${Post.bno}" />');
        console.log(serverMessage);
        console.log(processType);
        console.log(processState);

        document.getElementById('serverMessage').innerHTML = serverMessage;

        if (processState === "SUCCESS") {
            switch (processType) {
                case "Registration" :
                    serverMsgTag.textContent = "게시물(" + '<c:out value="${Post.bno}" />' + ")이 등록되었습니다.";
                    break;
                case "Read" :
                    break;
                case "Modify" :
                    serverMsgTag.textContent = "게시물이 수정되었습니다.";
                    break;
                case "Remove ALL" :
                    serverMsgTag.textContent = "모든 게시물이 삭제되었습니다.";
                    break;
                case "Remove" :
                    serverMsgTag.textContent = "게시물이 삭제되었습니다.";
                    break;
                // case "Login" :
                //     alert("로그인 성공");
                //     break;
                case "Logout" :
                    alert("정상적으로 로그아웃되었습니다.");
                    break;
                default :
                    serverMsgTag.textContent = "Fatal Error";
                    break;
            }
        } else if (processState === "WARNING") {
            switch (processType) {
                case "Registration" :
                    serverMsgTag.textContent = "게시물 등록을 할 수 없습니다.";
                    break;
                case "Modify" :
                    serverMsgTag.textContent = "게시물 수정을 할 수 없습니다.";
                    break;
                case "Remove ALL" :
                    serverMsgTag.textContent = "모든 게시물을 삭제할 수 없습니다.";
                    break;
                case "Remove" :
                    serverMsgTag.textContent = "게시물 삭제를 할 수 없습니다.";
                    break;
                case "Account" :
                    serverMsgTag.textContent = "접근 권한이 없습니다.";
                    break;
                default :
                    serverMsgTag.textContent = "Fatal Error";
                    break;
            }
        } else if (processState === "FAILURE") {
            switch (processType) {
                case "Registration" :
                    serverMsgTag.textContent = "게시물 등록을 실패했습니다.";
                    break;
                case "Read" :
                    document.querySelector('#errorMsg').innerHTML = "존재하지 않는 게시물입니다.";
                    break;
                case "Modify" :
                    serverMsgTag.textContent = "게시물 수정을 실패했습니다.";
                    break;
                case "Remove ALL" :
                    serverMsgTag.textContent = "모든 게시물 삭제를 실패했습니다.";
                    break;
                case "Remove" :
                    serverMsgTag.textContent = "게시물 삭제를 실패했습니다.";
                    break;
                case "Account" :
                    serverMsgTag.textContent = "접근 권한이 없습니다.";
                    break;
                default :
                    serverMsgTag.textContent = "Fatal Error";
                    break;
            }
        }
        // else {
        //     serverMsgTag.textContent = serverState;
        // }
    });
</script>
