const centerModal = new bootstrap.Modal(document.querySelector('#centerModal'), {});
const centerModalElem = document.querySelector('#centerModal');
const centerModalSubmit = centerModalElem.querySelector("#centerModalSubmit");

const switchToPasswordInputModal = function () {
    centerModalElem.querySelector(".modal-title").textContent = "비밀번호 확인";
    centerModalElem.querySelector(".modal-body").innerHTML = "<input type='password' name='password' placeholder='Password' class='form-control'/><p></p>";
    centerModalSubmit.classList.remove('btn-danger');
    centerModalSubmit.classList.add('btn-info');
    centerModalSubmit.textContent = "확인";
    centerModal.show();
}

const switchRetryModal = function (callback) {
    centerModalElem.querySelector('.modal-title').innerHTML = "Alert";
    centerModalElem.querySelector('.modal-body').innerHTML = "비밀번호가 일치하지않습니다.";
    centerModalElem.querySelector('#centerModalSubmit').classList.remove('btn-info');
    centerModalElem.querySelector('#centerModalSubmit').classList.add('btn-danger');
    centerModalElem.querySelector('#centerModalSubmit').innerHTML = "다시시도";
    centerModalSubmit.addEventListener('click', function () {
        switchToPasswordInputModal();
        callback();
    }, {once: true});
}