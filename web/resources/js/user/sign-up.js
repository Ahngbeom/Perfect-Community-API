const inputUserId = $("#inputUserId");
const inputPassword = $("#inputPassword");
const inputNickname = $("#inputNickname");
const checkAuthoritiesDiv = $("#checkAuthorities");
inputUserId.on('input', (e) => {
    const invalidFeedbackElem = $(e.target).parent("div").siblings(".invalid-feedback");
    const validFeedbackElem = $(e.target).parent("div").siblings(".valid-feedback");
    $.ajax({
        type: 'post',
        url: '/api/user/id-availability',
        async: 'false',
        data: inputUserId.val()
    }).done((data) => {
        if (data === true) {
            $(e.target).removeClass('is-invalid').addClass('is-valid');
            validFeedbackElem.css('display', 'block');
            invalidFeedbackElem.css('display', 'none');
        } else {
            validFeedbackElem.css('display', 'none');
            invalidFeedbackElem.css('display', 'block').text("중복된 ID입니다.");
            $(e.target).removeClass('is-valid').addClass('is-invalid');
        }
    }).fail((jqXHR) => {
        validFeedbackElem.css('display', 'none');
        invalidFeedbackElem.css('display', 'block').text(jqXHR.responseText);
        $(e.target).removeClass('is-valid').addClass('is-invalid');
    });
});

inputPassword.on('input', (e) => {
    const invalidFeedbackElem = $(e.target).parent("div").siblings(".invalid-feedback");
    $.ajax({
        type: 'post',
        url: '/api/user/password-availability',
        async: 'false',
        data: inputPassword.val()
    }).done(() => {
        $(e.target).removeClass('is-invalid').addClass('is-valid');
        invalidFeedbackElem.css('display', 'none');
    }).fail((jqXHR) => {
        invalidFeedbackElem.css('display', 'block').text(jqXHR.responseText);
        $(e.target).removeClass('is-valid').addClass('is-invalid');
    });
});

inputNickname.on('input', (e) => {
    const invalidFeedbackElem = $(e.target).parent("div").siblings(".invalid-feedback");
    const validFeedbackElem = $(e.target).parent("div").siblings(".valid-feedback");
    $.ajax({
        type: 'post',
        url: '/api/user/nickname-availability',
        async: 'false',
        data: inputNickname.val()
    }).done((data) => {
        if (data === true) {
            $(e.target).removeClass('is-invalid').addClass('is-valid');
            validFeedbackElem.css('display', 'block');
            invalidFeedbackElem.css('display', 'none');
        } else {
            validFeedbackElem.css('display', 'none');
            invalidFeedbackElem.css('display', 'block').text("중복된 Nickname입니다.");
            $(e.target).removeClass('is-valid').addClass('is-invalid');
        }
    }).fail((jqXHR) => {
        validFeedbackElem.css('display', 'none');
        invalidFeedbackElem.css('display', 'block').text(jqXHR.responseText);
        $(e.target).removeClass('is-valid').addClass('is-invalid');
    });
});

checkAuthoritiesDiv.on('change', "input[type='checkbox']", (e) => {
    if ($(e.target).is(":checked")) {
        $(e.target).addClass('is-valid');
    } else {
        $(e.target).removeClass('is-valid');
    }

    const checkedAuthorities = checkAuthoritiesDiv.find("input:checked").toArray().map(elem => $(elem).val());
    if ($.isEmptyObject(checkedAuthorities)) {
        console.log(checkAuthoritiesDiv.siblings(".invalid-feedback"));
        checkAuthoritiesDiv.find("input[type='checkbox']").addClass('is-invalid');
        checkAuthoritiesDiv.siblings(".invalid-feedback")
            .css('display', 'block');
    } else {
        checkAuthoritiesDiv.find("input[type='checkbox']").removeClass('is-invalid');
        checkAuthoritiesDiv.siblings(".invalid-feedback")
            .css('display', 'none');
    }
});

$("#signUpBtn").on('click', () => {
    const checkedAuthorities = checkAuthoritiesDiv.find("input:checked").toArray().map(elem => $(elem).val());
    if (inputUserId.hasClass("is-valid")
        && inputPassword.hasClass("is-valid")
        && inputNickname.hasClass("is-valid")
        && !$.isEmptyObject(checkedAuthorities)) {
        const signUpData = {
            userId: inputUserId.val(),
            password: inputPassword.val(),
            nickname: inputNickname.val(),
            authorities: checkedAuthorities
        }
        console.log(signUpData);
        $.ajax({
            type: 'post',
            url: '/api/user',
            async: false,
            data: JSON.stringify({
                userId: inputUserId.val(),
                password: inputPassword.val(),
                nickname: inputNickname.val(),
                authorities: checkedAuthorities
            })
        }).done((data) => {
            alert(data);
            location.href = "/";
        });
    } else {
        if (!inputUserId.hasClass("is-valid"))
            inputUserId.addClass("is-invalid");
        if (!inputPassword.hasClass("is-valid"))
            inputPassword.addClass("is-invalid");
        if (!inputNickname.hasClass("is-valid"))
            inputNickname.addClass("is-invalid");
        if ($.isEmptyObject(checkedAuthorities))
            checkAuthoritiesDiv.find("input[type='checkbox']").addClass('is-invalid');
    }
});