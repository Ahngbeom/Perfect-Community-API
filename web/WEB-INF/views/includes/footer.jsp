<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-04-07
  Time: 오전 12:47
  To change this template use File | Settings | File Templates.
--%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value="/resources/js/server/serverAlert.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/logInOut/Login.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/logInOut/Logout.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/board/boardForm.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/board/boardAlert.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/member/MemberForm.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/member/MemberControl.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/member/MemberAlert.js"/>"></script>
<script type="text/javascript">

    function putMessageForInputTag(type, inputSelector, message) {
        inputSelector.setAttribute("value-status", type);
        inputSelector.nextElementSibling.setAttribute("class", "text-" + type);
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

        console.log("Server Alert: " + "(TYPE: " + sessionStorage.getItem("boardAlertType") + ", STATUS: " + sessionStorage.getItem("alertStatus") + ")");

        let serverAlertTag = document.querySelector('#serverMessage');
        // serverAlertListener(serverAlertTag, "Login", [
        //     {
        //         level: "SUCCESS",
        //         message: null,
        //         flash: true
        //     },
        //     {level: "FAILURE", message: "존재하지 않는 계정이거나 비밀번호가 틀렸습니다.", flash: true}
        // ]);
        //
        // serverAlertListener(serverAlertTag, "Logout", [
        //     {level: "SUCCESS", message: "정상적으로 로그아웃되었습니다.", flash: true},
        //     {level: "FAILURE", message: "로그아웃을 실패했습니다.", flash: true}
        // ]);

        if (signListener(serverAlertTag, `${signAlertType}`, `${signAlertStatus}`)) {
            <% session.removeAttribute("signAlertType"); %>
            <% session.removeAttribute("signAlertStatus"); %>
        }
        boardFormChangeDetector();
        boardAlert(serverAlertTag, `${boardAlertType}`, `${boardAlertStatus}`);
        memberFormChangeDetector();
        memberControl(`${MemberList}`);
        memberAlert(serverAlertTag, `${memberAlertType}`, `${memberAlertStatus}`);

        // $("#centerModal").on('hide.bs.modal', function (e) {
        //     window.location.reload();
        // });

    });

</script>
