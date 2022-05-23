function memberAlert(serverAlertTag, type, status) {
    if (status === "SUCCESS") {
        switch (type) {
            case "Account Delete" :
                serverAlertTag.textContent = "계정이 정상적으로 삭제되었습니다.";
                break;
            case "Account Create" :
                serverAlertTag.textContent = "계정이 정상적으로 등록되었습니다. \n해당 계정으로 로그인해주세요.";
                break;
            default :
                break;
        }
    } else if (status === "WARNING") {
        switch (type) {
            case "Logout Required" :
                serverAlertTag.textContent = "로그아웃 후 진행해주세요.";
                break;
            case "Already Signed In" :
                serverAlertTag.textContent = "로그아웃 후 진행해주세요.";
                break;
            default :
                break;
        }
    } else if (status === "FAILURE") {
        switch (type) {
            case "Account Delete" :
                serverAlertTag.textContent = "계정 삭제를 실패했습니다.";
                break;
            case "Account Create" :
                serverAlertTag.textContent = "계정 등록을 실패했습니다.";
                break;
            case "Account Access" :
                serverAlertTag.textContent = "접근 권한이 없습니다.";
                break;
            default :
                break;
        }
    }

}