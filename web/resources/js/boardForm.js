function boardFormChangeDetector() {
    let formTag = document.querySelector('#boardForm');
    let writerInput = {
        selector : null,
        value : null
    };
    let titleInput = null;
    let contentInput = null;
    let isAnonymous = null;
    let submitBtn = null;
    if (formTag != null) {
        writerInput.selector = formTag.querySelector("input[name='writer']");
        writerInput.value = writerInput.selector.value;
        titleInput = formTag.querySelector("input[name='title']");
        contentInput = formTag.querySelector("textarea[name='content']");
        isAnonymous = formTag.querySelector("#boardRegisterFormWriterIsAnonymous");
        submitBtn = formTag.querySelector("#postRegisterBtn");
    }
    if (writerInput.selector != null) {
        writerInput.selector.addEventListener('input', (evt) => {
            if (evt.currentTarget.value === "") {
                console.log(writerInput.nextElementSibling);
                // HTMLTableElement.insertCell();
                putMessage('danger', writerInput, '작성자를 입력해주세요.');
                document.getElementById('serverMessage').innerHTML = '작성자를 입력해주세요.';
                document.getElementById('serverMessage').setAttribute("form-value-status", "EMPTY");
            } else {
                putMessage('success', writerInput, '');
                document.getElementById('serverMessage').setAttribute("form-value-status", "");
            }
        });
    }

    if (titleInput != null) {
        titleInput.addEventListener('input', (evt) => {
            if (evt.currentTarget.value === "") {
                // HTMLTableElement.insertCell();
                putMessage('danger', titleInput, '제목을 입력해주세요.');
                document.getElementById('serverMessage').innerHTML = '제목을 입력해주세요.';
                document.getElementById('serverMessage').setAttribute("form-value-status", "EMPTY");
            } else {
                putMessage('success', titleInput, '');
                document.getElementById('serverMessage').setAttribute("form-value-status", "");
            }
        });
    }
    if (contentInput != null) {
        contentInput.addEventListener('input', (evt) => {
            if (evt.currentTarget.value.length === 0) {
                putMessage('danger', contentInput, '내용을 입력해주세요.');
                // HTMLTableElement.insertCell();
                document.getElementById('serverMessage').innerHTML = '내용을 입력해주세요.';
                document.getElementById('serverMessage').setAttribute("form-value-status", "EMPTY");
            } else {
                putMessage('success', contentInput, '');
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

    if (submitBtn != null) {
        submitBtn.addEventListener('click', () => {
            console.log("submit");
            if (writerInput.value.length === 0)
                document.getElementById('serverMessage').innerHTML = '작성자를 입력해주세요.';
            else if (titleInput.value.length === 0)
                document.getElementById('serverMessage').innerHTML = '제목을 입력해주세요.';
            else if (contentInput.value.length === 0)
                document.getElementById('serverMessage').innerHTML = '내용을 입력해주세요.';
            else
                formTag.submit();
        });
    }
}
