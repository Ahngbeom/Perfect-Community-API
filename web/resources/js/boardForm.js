document.addEventListener('DOMContentLoaded', () => {
    let formTag = document.querySelector('form');
    let writerInput = null;
    let titleInput = null;
    let contentInput = null;
    let submitBtn = null;
    if (formTag != null) {
        writerInput = formTag.querySelector("input[name='writer']");
        titleInput = formTag.querySelector("input[name='title']");
        contentInput = formTag.querySelector("textarea[name='content']");
        submitBtn = formTag.querySelector("#postRegisterBtn");
    }
    if (writerInput != null) {
        writerInput.addEventListener('input', (evt) => {
            if (evt.currentTarget.value == "") {
                console.log(writerInput.closest('td'));
                // HTMLTableElement.insertCell();
                document.getElementById('serverMsg').innerHTML = '<p class="redText">작성자를 입력해주세요.</p>';
                document.getElementById('serverMsg').setAttribute("form-value-status", "EMPTY");
            } else {
                document.getElementById('serverMsg').setAttribute("form-value-status", "");
            }
        });
    }

    if (titleInput != null) {
        titleInput.addEventListener('input', (evt) => {
            if (evt.currentTarget.value == "") {
                console.log(writerInput.closest('td'));
                // HTMLTableElement.insertCell();
                document.getElementById('serverMsg').innerHTML = '<p class="redText">제목을 입력해주세요.</p>';
                document.getElementById('serverMsg').setAttribute("form-value-status", "EMPTY");
            } else {
                document.getElementById('serverMsg').setAttribute("form-value-status", "");
            }
        });
    }
    if (contentInput != null) {
        contentInput.addEventListener('input', (evt) => {
            if (evt.currentTarget.value.length == 0) {
                console.log(writerInput.closest('td'));
                // HTMLTableElement.insertCell();
                document.getElementById('serverMsg').innerHTML = '<p class="redText">내용을 입력해주세요.</p>';
                document.getElementById('serverMsg').setAttribute("form-value-status", "EMPTY");
            } else {
                document.getElementById('serverMsg').setAttribute("form-value-status", "");
            }
        });
    }

    if (submitBtn != null) {
        submitBtn.addEventListener('click', (evt) => {
            if (writerInput.value.length == 0)
                document.getElementById('serverMsg').innerHTML = '<p class="redText">작성자를 입력해주세요.</p>';
            else if (titleInput.value.length == 0)
                document.getElementById('serverMsg').innerHTML = '<p class="redText">제목을 입력해주세요.</p>';
            else if (contentInput.value.length == 0)
                document.getElementById('serverMsg').innerHTML = '<p class="redText">내용을 입력해주세요.</p>';
            else
                formTag.submit();
        });
    }

    let serverType = '<c:out value="${type}" />';
    let serverState = '<c:out value="${state}" />';
    let serverMsgTag = document.querySelector('#serverMsg');

    // console.log('<c:out value="${Post.bno}" />');
    // console.log(serverState);
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
