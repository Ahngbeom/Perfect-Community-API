<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-04-07
  Time: 오전 12:47
  To change this template use File | Settings | File Templates.
--%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value="/resources/js/utils/utilsFunction.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/utils/modal.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/server/serverAlert.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/logInOut/Login.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/logInOut/Logout.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/board/boardAlert.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/board/boardCRUD.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/board/boardForm.js"/>"></script>
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

        if (signListener(serverAlertTag, `${signAlertType}`, `${signAlertStatus}`)) {
            <% session.removeAttribute("signAlertType"); %>
            <% session.removeAttribute("signAlertStatus"); %>
        }
        boardFormChangeDetector();
        boardAlert(serverAlertTag, `${boardAlertType}`, `${boardAlertStatus}`, `${boardAlertMessage}`);
        memberFormChangeDetector();
        memberControl(`${MemberList}`);
        memberAlert(serverAlertTag, `${memberAlertType}`, `${memberAlertStatus}`);

        // $("#centerModal").on('hide.bs.modal', function (e) {
        //     window.location.reload();
        // });

    });

</script>
