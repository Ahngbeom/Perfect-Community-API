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
            if (evt.currentTarget.value === "") {
                console.log(writerInput.closest('td'));
                // HTMLTableElement.insertCell();
                document.getElementById('serverMessage').innerHTML = '<p class="redText">작성자를 입력해주세요.</p>';
                document.getElementById('serverMessage').setAttribute("form-value-status", "EMPTY");
            } else {
                document.getElementById('serverMessage').setAttribute("form-value-status", "");
            }
        });
    }

    if (titleInput != null) {
        titleInput.addEventListener('input', (evt) => {
            if (evt.currentTarget.value === "") {
                console.log(writerInput.closest('td'));
                // HTMLTableElement.insertCell();
                document.getElementById('serverMessage').innerHTML = '<p class="redText">제목을 입력해주세요.</p>';
                document.getElementById('serverMessage').setAttribute("form-value-status", "EMPTY");
            } else {
                document.getElementById('serverMessage').setAttribute("form-value-status", "");
            }
        });
    }
    if (contentInput != null) {
        contentInput.addEventListener('input', (evt) => {
            if (evt.currentTarget.value.length === 0) {
                console.log(writerInput.closest('td'));
                // HTMLTableElement.insertCell();
                document.getElementById('serverMessage').innerHTML = '<p class="redText">내용을 입력해주세요.</p>';
                document.getElementById('serverMessage').setAttribute("form-value-status", "EMPTY");
            } else {
                document.getElementById('serverMessage').setAttribute("form-value-status", "");
            }
        });
    }

    if (submitBtn != null) {
        submitBtn.addEventListener('click', () => {
            if (writerInput.value.length === 0)
                document.getElementById('serverMessage').innerHTML = '<p class="redText">작성자를 입력해주세요.</p>';
            else if (titleInput.value.length === 0)
                document.getElementById('serverMessage').innerHTML = '<p class="redText">제목을 입력해주세요.</p>';
            else if (contentInput.value.length === 0)
                document.getElementById('serverMessage').innerHTML = '<p class="redText">내용을 입력해주세요.</p>';
            else
                formTag.submit();
        });
    }

});
