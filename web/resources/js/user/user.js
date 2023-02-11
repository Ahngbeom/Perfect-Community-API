let userDetails;

const userForm = $("#userForm");
const userPasswordVerifyForm = $("#userPasswordVerifyForm");
const userPasswordChangeForm = $("#userPasswordChangeForm");
const userSecessionConfirm = $("#userSecessionConfirm");
const userIdInputElem = $("#userId");
const nicknameInputElem = $("#nickname");
const authoritiesCheckboxesElem = $("#authorities");
const enabledSwitchElem = $("#enabled");

const userUpdateBtn = $("#userUpdateBtn");

function getUserInfo(userId) {
    $.ajax({
        type: 'get',
        url: '/api/user/' + userData.username,
        async: false
    }).done((data) => {
        // console.log(data);
        userDetails = data;
    });
}

function putUserInfo() {
    userForm.removeClass("visually-hidden");
    userPasswordVerifyForm.addClass("visually-hidden");
    userPasswordChangeForm.addClass("visually-hidden");
    userSecessionConfirm.addClass("visually-hidden");
    if (!$.isEmptyObject(userData)) {
        getUserInfo(userData.username);
        userIdInputElem.val(userDetails.userId).attr("readonly", true);
        nicknameInputElem.val(userDetails.nickname)
            .removeClass("form-control")
            .addClass("form-control-plaintext")
            .attr("readonly", true);
        for (let authoritiesCheckboxElem of authoritiesCheckboxesElem.find("input")) {
            $(authoritiesCheckboxElem).attr("disabled", true);
            if ($.inArray($(authoritiesCheckboxElem).val(), userDetails.authorities) >= 0)
                $(authoritiesCheckboxElem).attr("checked", true);
        }
        enabledSwitchElem.attr("checked", userDetails.enabled).attr("disabled", true);
    }
}

function putUserInfoUpdateForm() {
    userForm.removeClass("visually-hidden");
    userPasswordVerifyForm.addClass("visually-hidden");
    userPasswordChangeForm.addClass("visually-hidden");
    userSecessionConfirm.addClass("visually-hidden");

    // userIdInputElem
    //     .removeClass("form-control-plaintext")
    //     .addClass("form-control")
    //     .attr("readonly", false);
    nicknameInputElem
        .removeClass("form-control-plaintext")
        .addClass("form-control").attr("readonly", false);
    for (let authoritiesCheckboxElem of authoritiesCheckboxesElem.find("input")) {
        $(authoritiesCheckboxElem).attr("disabled", false);
    }
    enabledSwitchElem.attr("checked", userDetails.enabled).attr("disabled", false);
    userUpdateBtn.removeClass("visually-hidden");
    userUpdateBtn.one('click', () => {
        console.log({
            userId: userIdInputElem.val(),
            nickname: nicknameInputElem.val(),
            authorities: authoritiesCheckboxesElem.find("input:checked").toArray().map(elem => $(elem).val()),
            enabled: enabledSwitchElem.is(":checked"),
        });

        $.ajax({
            type: 'patch',
            url: '/api/user/' + userData.username,
            dataType: 'text',
            data: JSON.stringify({
                nickname: nicknameInputElem.val(),
                authorities: authoritiesCheckboxesElem.find("input:checked").toArray().map(elem => $(elem).val()),
                enabled: enabledSwitchElem.is(":checked"),
            })
        }).done(() => {
            location.reload();
        })
    });
}

function putUserPasswordChangeForm() {

    userForm.addClass("visually-hidden");
    userPasswordVerifyForm.addClass("visually-hidden");
    userSecessionConfirm.addClass("visually-hidden");
    userPasswordChangeForm.removeClass("visually-hidden");
}

function putUserSecessionConfirm() {
    userForm.addClass("visually-hidden");
    userPasswordVerifyForm.addClass("visually-hidden");
    userPasswordChangeForm.addClass("visually-hidden");
    userSecessionConfirm.removeClass("visually-hidden");
}

function putUserPasswordVerifyForm() {
    userForm.addClass("visually-hidden");
    userPasswordVerifyForm.removeClass("visually-hidden");
}

function userPasswordVerify(callback) {
    userPasswordVerifyForm.on('click', 'button', () => {
        const password = userPasswordVerifyForm.find("input[type='password']").val();
        console.log(password);
        $.ajax({
            type: 'post',
            url: '/api/user/verify-password/' + userData.username,
            data: password
        }).done((data) => {
            if (data === false) {
                alert("비밀번호가 일치하지 않습니다.");
            } else {
                console.log(data);
                console.log(typeof callback);
                callback();
            }
        });
    });
}

$("#shoUserInfoFormBtn").on('click', putUserInfo);
$("#showUserUpdateFormBtn").on('click', putUserInfoUpdateForm);
$("#showUserPasswordChangeFormBtn").on('click', () => {
    putUserPasswordVerifyForm();
    userPasswordVerify(putUserPasswordChangeForm);
});
$("#showUserSecessionFormBtn").on('click', () => {
    putUserPasswordVerifyForm();
    userPasswordVerify(putUserSecessionConfirm);
});
