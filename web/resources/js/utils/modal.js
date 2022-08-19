const centerModal = new bootstrap.Modal(document.querySelector('#centerModal'), {});
const centerModalElem = document.querySelector('#centerModal');
const centerModalSubmit = centerModalElem.querySelector("#centerModalSubmit");

const switchToPasswordInputModal = function () {
    centerModalElem.querySelector(".modal-title").textContent = "비밀번호 확인";
    centerModalElem.querySelector(".modal-body").innerHTML = "<input type='password' name='password' placeholder='Password' class='form-control'/><p></p>";
    centerModalSubmit.classList.remove('btn-danger');
    centerModalSubmit.classList.add('btn-info');
    centerModalSubmit.textContent = "확인";
    // $("#centerModal").modal();
    centerModal.show();

    centerModalSubmit.addEventListener('click', function () {
        ajaxPostDelete({
            bno: document.querySelector("#postsForm").querySelector("input[name='bno']").value,
            boardPassword: centerModalElem.querySelector(".modal-body input[name='password']").value
        });
    }, {once: true});
}