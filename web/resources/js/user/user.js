let userDetails;

const userForm = $("#userForm")
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
    if (!$.isEmptyObject(userData)) {
        getUserInfo(userData.username);
        userIdInputElem.val(userDetails.userId).attr("readonly", true);
        nicknameInputElem.val(userDetails.nickname).attr("readonly", true);
        for (let authoritiesCheckboxElem of authoritiesCheckboxesElem.find("input")) {
            $(authoritiesCheckboxElem).attr("disabled", true);
            if ($.inArray($(authoritiesCheckboxElem).val(), userDetails.authorities) >= 0)
                $(authoritiesCheckboxElem).attr("checked", true);
        }
        enabledSwitchElem.attr("checked", userDetails.enabled).attr("disabled", true);
    }
}

function putUserInfoUpdateForm() {
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
            data: JSON.stringify({
                nickname: nicknameInputElem.val(),
                authorities: authoritiesCheckboxesElem.find("input:checked").toArray().map(elem => $(elem).val()),
                enabled: enabledSwitchElem.is(":checked"),
            })
        }).done(() => {
            location.reload();
        }).fail((jqXHR) => {
            alert(jqXHR.responseText);
        })
    });
}

$("#shoUserInfoFormBtn").on('click', putUserInfo);
$("#showUserUpdateFormBtn").on('click', putUserInfoUpdateForm);
$("#showUserPasswordChangeFormBtn").on('click', putUserInfo);
$("#showUserSecessionFormBtn").on('click', putUserInfo);
