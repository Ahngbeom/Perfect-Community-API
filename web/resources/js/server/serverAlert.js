function putServerAlert(message) {
    let serverAlertTag = document.querySelector('#serverMessage');
    serverAlertTag.textContent = message;
}

function serverAlertListener(serverAlertTag, type, alertLevelMap) {
    if (sessionStorage.getItem("boardAlertType") === type) {
        for (let i = 0; i < alertLevelMap.length; i++) {
            if (sessionStorage.getItem("alertStatus") === alertLevelMap.at(i).level) {
                if (alertLevelMap.at(i).message != null)
                    putServerAlert(alertLevelMap.at(i).message);
                if (alertLevelMap.at(i).flash) {
                    sessionStorage.removeItem("boardAlertType");
                    sessionStorage.removeItem("alertStatus");
                }
                break;
            }
        }
    }
}

function signListener(serverAlertTag, type, status) {
    if (type === "Login") {
        switch (status) {
            case "Invalid Accounts | Bad Credentials":
                serverAlertTag.textContent = "존재하지 않는 계정이거나 비밀번호가 틀렸습니다.";
                break;
            case "Account is Disabled":
                serverAlertTag.textContent = "해당 계정은 비활성화 상태입니다."; // 활성화 경로 안내 필요
                break;                
            case "FAILURE":
                serverAlertTag.textContent = "로그인 실패";
                break;
            default:
                break;
        }
    } else if (type === "Logout") {
        switch (status) {
            case "SUCCESS":
                serverAlertTag.textContent = "정상적으로 로그아웃되었습니다.";
                break;
            default:
                break;
        }
    } else {
        return false;
    }
    return true;
}