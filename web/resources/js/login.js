const loginModalElem = $("#loginModal");
const loginModal = new bootstrap.Modal(loginModalElem);
const loginSubmitBtn = loginModalElem.find(".modal-footer button");

loginModalElem.on('show.bs.modal', () => {

});

function ajaxLogin() {
    return $.ajax({
        type: 'post',
        url: '/login',
        async: false,
        data: {
            username: loginModalElem.find("input[name='username']").val(),
            password: loginModalElem.find("input[name='password']").val()
        }
    });
}
