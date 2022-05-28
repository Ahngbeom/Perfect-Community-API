function boardAlert(serverAlertTag, type, status, message) {
    if (status === "SUCCESS") {
        switch (type) {
            case "Board Registration" :
                serverAlertTag.textContent = "게시물이 정상적으로 등록되었습니다.";
                break;
            case "Board Read" :
                break;
            case "Board Modify" :
                serverAlertTag.textContent = "게시물이 정상적으로 수정되었습니다.";
                break;
            case "Board Remove ALL" :
                serverAlertTag.textContent = "모든 게시물이 정상적으로 삭제되었습니다.";
                break;
            case "Board Remove" :
                serverAlertTag.textContent = "게시물이 정상적으로 삭제되었습니다.";
                break;
            case "Board Search" :
                serverAlertTag.textContent = message;
                break;
            default :
                serverAlertTag.textContent = "Fatal Error";
                break;
        }
    } else if (status === "WARNING") {
        switch (type) {
            case "Board Registration" :
                serverAlertTag.textContent = "게시물 등록을 할 수 없습니다.";
                break;
            case "Board Modify" :
                serverAlertTag.textContent = "게시물 수정을 할 수 없습니다.";
                break;
            case "Board Remove ALL" :
                serverAlertTag.textContent = "모든 게시물을 삭제할 수 없습니다.";
                break;
            case "Board Remove" :
                serverAlertTag.textContent = "게시물 삭제를 할 수 없습니다.";
                break;
            default :
                serverAlertTag.textContent = "Fatal Error";
                break;
        }
    } else if (status === "FAILURE") {
        switch (type) {
            case "Board Registration" :
                serverAlertTag.textContent = "게시물 등록을 실패했습니다.";
                break;
            case "Board Read" :
                document.querySelector('#errorMsg').innerHTML = "존재하지 않는 게시물입니다.";
                break;
            case "Board Modify" :
                serverAlertTag.textContent = "게시물 수정을 실패했습니다.";
                break;
            case "Board Remove ALL" :
                serverAlertTag.textContent = "모든 게시물 삭제를 실패했습니다.";
                break;
            case "Board Remove" :
                serverAlertTag.textContent = "게시물 삭제를 실패했습니다.";
                break;
            case "Board Search" :
                serverAlertTag.textContent = message;
                break;
            default :
                serverAlertTag.textContent = "Fatal Error";
                break;
        }
    }
}