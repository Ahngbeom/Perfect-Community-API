<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-04-07
  Time: 오전 12:47
  To change this template use File | Settings | File Templates.
--%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Login.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Logout.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/boardForm.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/MemberForm.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/MemberControl.js"/>"></script>
<script type="text/javascript">

    function serverAlertListener(serverAlertTag, type, alertLevelMap) {

        let principalUserId;

        if (${isAuthorizeAny}) {
            principalUserId = '${principalUserId}';
            console.log("User ID: " + principalUserId);
        }

        if (sessionStorage.getItem("alertType") === type) {
            for (let i = 0; i < alertLevelMap.length; i++) {
                if (sessionStorage.getItem("alertStatus") === alertLevelMap.at(i).level) {
                    serverAlertTag.textContent = alertLevelMap.at(i).message;
                    // console.log(alertLevelMap.at(i).message);

                    console.log(sessionStorage.getItem("alertStatus"));

                    sessionStorage.removeItem("type");
                    sessionStorage.removeItem("state");
                    sessionStorage.removeItem("alertType");
                    sessionStorage.removeItem("alertStatus");

                    console.log(sessionStorage.getItem("alertStatus"));
                }
            }
        } else {
            serverAlertTag.textContent = "";
        }

    }

    <%--function serverMessageListener(serverAlert) {--%>
    <%--    let serverMessage = '<c:out value="${serverMessage}" />';--%>
    <%--    let processType = '<c:out value="${type}" />';--%>
    <%--    let processState = '<c:out value="${state}" />';--%>
    <%--    let serverMsgTag = document.querySelector('#serverMessage');--%>

    <%--    let principalUserId;--%>

    <%--    // console.log("SERVER: " + serverMessage + " (TYPE: " + processType + ", STATE: " + processState + ")");--%>
    <%--    if (${isAuthorizeAny}) {--%>
    <%--        principalUserId = '${principalUserId}';--%>
    <%--        // console.log("User ID: " + principalUserId);--%>
    <%--    }--%>

    <%--    document.getElementById('serverMessage').innerHTML = serverMessage;--%>

    <%--    if (processState === "SUCCESS") {--%>
    <%--        switch (processType) {--%>
    <%--            case "Registration" :--%>
    <%--                serverMsgTag.textContent = "게시물(" + '<c:out value="${Post.bno}" />' + ")이 등록되었습니다.";--%>
    <%--                break;--%>
    <%--            case "Read" :--%>
    <%--                break;--%>
    <%--            case "Modify" :--%>
    <%--                serverMsgTag.textContent = "게시물이 수정되었습니다.";--%>
    <%--                break;--%>
    <%--            case "Remove ALL" :--%>
    <%--                serverMsgTag.textContent = "모든 게시물이 삭제되었습니다.";--%>
    <%--                break;--%>
    <%--            case "Remove" :--%>
    <%--                serverMsgTag.textContent = "게시물이 삭제되었습니다.";--%>
    <%--                break;--%>
    <%--            case "Login" :--%>
    <%--                if (${not empty isAdmin})--%>
    <%--                    serverMsgTag.textContent = "Hello 👑[" + principalUserId + "]👑";--%>
    <%--                else--%>
    <%--                    serverMsgTag.textContent = "Hello [" + principalUserId + "]";--%>
    <%--                break;--%>
    <%--            case "Account Delete" :--%>
    <%--                serverMsgTag.textContent = '<c:out value="${userId}" />' + " 계정이 정상적으로 삭제되었습니다.";--%>
    <%--                break;--%>
    <%--            case "Account Create" :--%>
    <%--                serverMsgTag.textContent = '<c:out value="${userId}" />' + " 계정이 정상적으로 등록되었습니다. \n해당 계정으로 로그인해주세요.";--%>
    <%--                break;--%>
    <%--            default :--%>
    <%--                serverMsgTag.textContent = "Fatal Error";--%>
    <%--                break;--%>
    <%--        }--%>
    <%--    } else if (processState === "WARNING") {--%>
    <%--        switch (processType) {--%>
    <%--            case "Registration" :--%>
    <%--                serverMsgTag.textContent = "게시물 등록을 할 수 없습니다.";--%>
    <%--                break;--%>
    <%--            case "Modify" :--%>
    <%--                serverMsgTag.textContent = "게시물 수정을 할 수 없습니다.";--%>
    <%--                break;--%>
    <%--            case "Remove ALL" :--%>
    <%--                serverMsgTag.textContent = "모든 게시물을 삭제할 수 없습니다.";--%>
    <%--                break;--%>
    <%--            case "Remove" :--%>
    <%--                serverMsgTag.textContent = "게시물 삭제를 할 수 없습니다.";--%>
    <%--                break;--%>
    <%--            case "Account" :--%>
    <%--                serverMsgTag.textContent = "접근 권한이 없습니다.";--%>
    <%--                break;--%>
    <%--            case "Account Create" :--%>
    <%--                serverMsgTag.textContent = '<c:out value="${userId}" />' + " 계정이 정상적으로 등록되었습니다.";--%>
    <%--                break;--%>
    <%--            case "Logout Required" :--%>
    <%--                serverMsgTag.textContent = "로그아웃 후 진행해주세요.";--%>
    <%--                break;--%>
    <%--            default :--%>
    <%--                serverMsgTag.textContent = "Fatal Error";--%>
    <%--                break;--%>
    <%--        }--%>
    <%--    } else if (processState === "FAILURE") {--%>
    <%--        switch (processType) {--%>
    <%--            case "Registration" :--%>
    <%--                serverMsgTag.textContent = "게시물 등록을 실패했습니다.";--%>
    <%--                break;--%>
    <%--            case "Read" :--%>
    <%--                document.querySelector('#errorMsg').innerHTML = "존재하지 않는 게시물입니다.";--%>
    <%--                break;--%>
    <%--            case "Modify" :--%>
    <%--                serverMsgTag.textContent = "게시물 수정을 실패했습니다.";--%>
    <%--                break;--%>
    <%--            case "Remove ALL" :--%>
    <%--                serverMsgTag.textContent = "모든 게시물 삭제를 실패했습니다.";--%>
    <%--                break;--%>
    <%--            case "Remove" :--%>
    <%--                serverMsgTag.textContent = "게시물 삭제를 실패했습니다.";--%>
    <%--                break;--%>
    <%--            case "Account" :--%>
    <%--                serverMsgTag.textContent = "접근 권한이 없습니다.";--%>
    <%--                break;--%>
    <%--            default :--%>
    <%--                serverMsgTag.textContent = "Fatal Error";--%>
    <%--                break;--%>
    <%--        }--%>
    <%--    }--%>
    <%--    else {--%>
    <%--        serverMsgTag.textContent = "";--%>
    <%--    }--%>
    <%--}--%>


    // function logoutListener() {
    //     let serverMsgTag = document.querySelector('#serverMessage');
    //     if (sessionStorage.getItem("type") != null && sessionStorage.getItem("type") === 'Logout') {
    //         if (sessionStorage.getItem("state") != null && sessionStorage.getItem("state") === 'SUCCESS') {
    //             serverMsgTag.textContent = "정상적으로 로그아웃되었습니다.";
    //             sessionStorage.removeItem("type");
    //             sessionStorage.removeItem("state");
    //         } else {
    //             serverMsgTag.textContent = "로그아웃을 실패했습니다.";
    //             sessionStorage.removeItem("type");
    //             sessionStorage.removeItem("state");
    //         }
    //     }
    // }

    function putMessage(type, inputSelector, message) {
        inputSelector.setAttribute("value-status", type);
        inputSelector.nextElementSibling.classList.add("text-" + type);
        switch (type) {
            case "danger" :
                inputSelector.classList.remove("is-valid");
                inputSelector.classList.add("is-invalid");
                break;
            case "success" :
                inputSelector.classList.remove("is-invalid");
                inputSelector.classList.add("is-valid");
                break;
            default :
                break;
        }
        inputSelector.nextElementSibling.innerHTML = message;
    }

    document.addEventListener('DOMContentLoaded', () => {

        <%--sessionStorage.setItem("alertType", '<c:out value="${alertType}" />' != null ? '<c:out value="${alertType}" />' : null);--%>
        <%--sessionStorage.setItem("alertStatus", `<c:out value="${alertStatus}" />` != null ? `<c:out value="${alertStatus}" />` : null);--%>
        // sessionStorage.setItem("putServerAlertTag", document.querySelector('#serverMessage'));
        <%--let serverAlert = {--%>
        <%--    alertType: '<c:out value="${alertType}" />' != null ? '<c:out value="${alertType}" />' : null,--%>
        <%--    alertStatus: `<c:out value="${alertStatus}" />` != null ? `<c:out value="${alertStatus}" />` : undefined,--%>
        <%--    putServerAlertTag: document.querySelector('#serverMessage')--%>
        <%--}--%>
        console.log("Server Alert: " + "(TYPE: " + sessionStorage.getItem("alertType") + ", STATUS: " + sessionStorage.getItem("alertStatus") + ")");
        console.log(sessionStorage.getItem("alertType"));
        console.log(sessionStorage.getItem("alertStatus"));

        let serverAlertTag = document.querySelector('#serverMessage');

        let principalUserId = ${isAuthorizeAny} ? `${principalUserId}` : null;

        serverAlertListener(serverAlertTag, "Login", [
            { level: "SUCCESS", message: ${not empty isAdmin} ? "Hello 👑[" + principalUserId + "]👑" : "" },
            { level: "Invalid Accounts | Bad Credentials", message: "존재하지 않는 계정이거나 비밀번호가 틀렸습니다."},
            { level: "FAILURE", message: "로그인 실패"}
        ]);

        serverAlertListener(serverAlertTag, "Logout", [
            { level: "SUCCESS", message: "정상적으로 로그아웃되었습니다." },
            { level: "FAILURE", message: "로그아웃을 실패했습니다."}
        ]);
        // logoutListener();

        // serverMessageListener();
        boardFormChangeDetector();
        memberFormChangeDetector();
        memberControl();
    });

</script>
