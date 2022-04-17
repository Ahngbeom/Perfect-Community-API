<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-04-07
  Time: 오전 12:47
  To change this template use File | Settings | File Templates.
--%>
<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', () => {
        let serverType = '<c:out value="${type}" />';
        let serverState = '<c:out value="${state}" />';
        let serverMsgTag = document.querySelector('#serverMsg');

        <%--console.log('<c:out value="${Post.bno}" />');--%>
        <%--console.log(serverState);--%>
        if (serverState === "SUCCESS") {
            switch (serverType) {
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
                default :
                    serverMsgTag.textContent = "Fatal Error";
                    break;
            }
        }
        else if (serverState === "WARNING") {
            switch (serverType) {
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
                default :
                    serverMsgTag.textContent = "Fatal Error";
                    break;
            }
        }
        else if (serverState === "FAILURE") {
            switch (serverType) {
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
                default :
                    serverMsgTag.textContent = "Fatal Error";
                    break;
            }
        }
        else {
            serverMsgTag.textContent = serverState;
        }
    });
</script>
