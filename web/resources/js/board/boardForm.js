function boardFormChangeDetector() {
    let formTag = document.querySelector('#boardForm');
    let writerInput = {
        selector : null,
        value : null
    };
    let titleInput = null;
    let contentInput = null;
    let isAnonymous = null;
    let passwordInput = null;
    let submitBtn = null;
    if (formTag != null) {
        writerInput.selector = formTag.querySelector("input[name='writer']");
        writerInput.value = writerInput.selector.value;
        titleInput = formTag.querySelector("input[name='title']");
        contentInput = formTag.querySelector("textarea[name='content']");
        isAnonymous = formTag.querySelector("#boardRegisterFormWriterIsAnonymous");
        passwordInput = formTag.querySelector("#boardRegisterFormPassword");
        submitBtn = formTag.querySelector("#postRegisterBtn");
    }
    if (writerInput.selector != null) {
        writerInput.selector.addEventListener('input', (evt) => {
            if (evt.currentTarget.value === "") {
                console.log(writerInput.nextElementSibling);
                // HTMLTableElement.insertCell();
                putMessageForInputTag('danger', writerInput.selector, '작성자를 입력해주세요.');
                document.getElementById('serverMessage').innerHTML = '작성자를 입력해주세요.';
                document.getElementById('serverMessage').setAttribute("form-value-status", "EMPTY");
            } else {
                let writer = evt.currentTarget.value;
                let isDuplicates = false;

                let httpRequest = new XMLHttpRequest();
                httpRequest.onreadystatechange = () => {
                    if (httpRequest.readyState === XMLHttpRequest.DONE) {
                        if (httpRequest.status === 200) {
                            console.log(httpRequest.response);
                            if (httpRequest.response === 'true')
                                putMessageForInputTag("danger", writerInput.selector, "이미 존재하는 계정의 이름입니다.");
                            else
                                putMessageForInputTag('success', writerInput.selector, '');
                        } else {
                            // console.log("HTTP Request(status) ERROR: " + httpRequest.status);
                        }
                    } else {
                        // console.log("HTTP Request(readyState) ERROR: " + httpRequest.readyState);
                    }
                };

                if (formatValidator("ID", writer)) {
                    putMessageForInputTag("danger", writerInput.selector, "작성자는 영문자, 숫자, \'_\' 만 입력 가능합니다.")
                } else if (writer.length < 2) {
                    putMessageForInputTag("danger", writerInput.selector, "작성자는 최소 2자 이상이어야 합니다.");
                } else {
                    // putMessageForInputTag("success", writerInput.selector, "");
                    // httpRequest.open('GET', '/check/userid/duplicates?userId=' + encodeURIComponent(inputValue));
                    // httpRequest.send(null);
                    httpRequest.open('POST', '/member/userId-duplicatesCheck');
                    httpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                    httpRequest.send("userId=" + encodeURIComponent(writer));
                }
            }
        });
    }

    if (titleInput != null) {
        titleInput.addEventListener('input', (evt) => {
            if (evt.currentTarget.value === "") {
                // HTMLTableElement.insertCell();
                putMessageForInputTag('danger', titleInput, '제목을 입력해주세요.');
                document.getElementById('serverMessage').innerHTML = '제목을 입력해주세요.';
                document.getElementById('serverMessage').setAttribute("form-value-status", "EMPTY");
            } else {
                putMessageForInputTag('success', titleInput, '');
                document.getElementById('serverMessage').setAttribute("form-value-status", "");
            }
        });
    }
    if (contentInput != null) {
        contentInput.addEventListener('input', (evt) => {
            if (evt.currentTarget.value.length === 0) {
                putMessageForInputTag('danger', contentInput, '내용을 입력해주세요.');
                // HTMLTableElement.insertCell();
                document.getElementById('serverMessage').innerHTML = '내용을 입력해주세요.';
                document.getElementById('serverMessage').setAttribute("form-value-status", "EMPTY");
            } else {
                putMessageForInputTag('success', contentInput, '');
                document.getElementById('serverMessage').setAttribute("form-value-status", "");
            }
        });
    }

    if (isAnonymous != null) {
        isAnonymous.addEventListener('input', () => {
           if (isAnonymous.checked) {
               writerInput.selector.value = '익명';
           } else {
               writerInput.selector.value = writerInput.value;
           }
        });
    }

    if (passwordInput != null) {
        passwordInput.addEventListener('input', (evt) => {
            if (evt.currentTarget.value.length === 0) {
                putMessageForInputTag('danger', passwordInput, '비밀번호를 입력해주세요.');
                // HTMLTableElement.insertCell();
                document.getElementById('serverMessage').innerHTML = '비밀번호를 입력해주세요.';
                document.getElementById('serverMessage').setAttribute("form-value-status", "EMPTY");
            } else {
                putMessageForInputTag('success', passwordInput, '');
                document.getElementById('serverMessage').setAttribute("form-value-status", "");
            }
        });
    }

    if (submitBtn != null) {
        submitBtn.addEventListener('click', () => {
            console.log("submit");
            if (writerInput.selector.value.length === 0)
                document.getElementById('serverMessage').innerHTML = '작성자를 입력해주세요.';
            else if (titleInput.value.length === 0)
                document.getElementById('serverMessage').innerHTML = '제목을 입력해주세요.';
            else if (contentInput.value.length === 0)
                document.getElementById('serverMessage').innerHTML = '내용을 입력해주세요.';
            else if (passwordInput && passwordInput.value.length === 0)
                document.getElementById('serverMessage').innerHTML = '비밀번호를 입력해주세요.';
            else
                formTag.submit();
        });
    }
}
