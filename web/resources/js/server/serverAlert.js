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