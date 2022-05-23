function memberControl(Member) {
    const adminUpdateAuthBtnCollect = document.querySelectorAll(".admin-update-auth-btn");
    const adminDeleteAllAuthBtnCollect = document.querySelectorAll(".admin-deleteAll-auth-btn");
    const adminDeleteMemBtnCollect = document.querySelectorAll(".admin-delete-member-btn");

    const centerModal = $("#centerModal");
    // const centerModal = document.querySelectorAll("#centerModal");

    adminUpdateAuthBtnCollect.forEach(adminAddAuthBtn => {
        adminAddAuthBtn.addEventListener('click', evt => {
            const form = adminAddAuthBtn.parentElement;
            centerModal.find("#centerModalTitle").text("권한 추가");
            centerModal.find(".modal-body").html(
                "<div class='h6' id='possessedAuth'>현재 보유 권한: </div>" +
                "<div class=\"custom-control custom-control-inline custom-checkbox\">\n" +
                "                    <input class=\"custom-control-input\" type=\"checkbox\" value=\"ROLE_ADMIN\" id='ROLE_ADMIN_checkbox'>\n" +
                "                    <label class=\"custom-control-label\" for='ROLE_ADMIN_checkbox'>ROLE_ADMIN</label>\n" +
                "                </div>\n" +
                "                <div class=\"custom-control custom-control-inline custom-checkbox\" onclick=''>\n" +
                "                    <input class=\"custom-control-input\" type=\"checkbox\"\n" +
                "                           value=\"ROLE_MANAGER\" id='ROLE_MANAGER_checkbox'>\n" +
                "                    <label class=\"custom-control-label\" for='ROLE_MANAGER_checkbox'>ROLE_MANAGER</label>\n" +
                "                </div>\n" +
                "                <div class=\"custom-control custom-control-inline custom-checkbox\">\n" +
                "                    <input class=\"custom-control-input\" type=\"checkbox\" value=\"ROLE_USER\" id='ROLE_USER_checkbox'>\n" +
                "                    <label class=\"custom-control-label\" for='ROLE_USER_checkbox'>ROLE_USER</label>\n" +
                "                </div>");
            centerModal.find("#centerModalSubmit").text("Submit");

            let formData = new Map();
            document.querySelectorAll(".custom-control-input").forEach(checkInput => {
                checkInput.addEventListener('change', (evt) => {
                    formData.set(evt.currentTarget.value, evt.currentTarget.checked);
                    console.log(formData);
                });
            });

            const userID = form.querySelector("input[name='userId']").value;
            centerModal.on('show.bs.modal', function (e) {
                $.ajax({
                    url: "/member/info/json",
                    type: "GET",
                    dataType: "JSON",
                    data: {userId: userID},
                    success: function (data) {
                        data.member.authList.forEach(auth => {
                            centerModal.find("#possessedAuth").append(" " + auth.auth + " ");
                            centerModal.find("#" + auth.auth + "_checkbox").attr("checked", true);
                        });
                        form.setAttribute("method", "POST");
                        form.setAttribute("action", "/member/auth/add");
                    },
                    complete: function () {
                        formData.set('ROLE_ADMIN', document.querySelector("#ROLE_ADMIN_checkbox").checked);
                        formData.set('ROLE_MANAGER', document.querySelector("#ROLE_MANAGER_checkbox").checked);
                        formData.set('ROLE_USER', document.querySelector("#ROLE_USER_checkbox").checked);
                    }
                });
                $(this).find("#centerModalSubmit")[0].addEventListener('click', () => {
                    formData.forEach((value, key) => {
                        let ajaxData = {userId: userID, auth: key, isAdd: value};
                        $.ajax({
                            url: "/member/auth/update",
                            type: "POST",
                            dataType: "JSON",
                            data: ajaxData,
                            success: function () {
                                window.location.reload();
                            }
                        });
                    });


                });
            });
            centerModal.modal();
        });
    });

    adminDeleteAllAuthBtnCollect.forEach(adminDeleteAllAuthBtn => {
        adminDeleteAllAuthBtn.addEventListener('click', evt => {
            centerModal.find("#centerModalTitle").text("모든 권한 삭제");
            centerModal.find(".modal-body").html(
                "<span class='text-danger font-weight-bold'>모든 권한을 삭제하시겠습니까?</span>");
            centerModal.find("#centerModalSubmit").removeClass("btn-info").addClass("btn-danger").text("Accept");
            centerModal.modal();

            $("#centerModalSubmit")[0].addEventListener('click', () => {
                const USER_ID = adminDeleteAllAuthBtn.parentElement.querySelector("input[name='userId']").value;
                $.ajax({
                    url: "/member/auth/removeAll",
                    type: "POST",
                    dataType: "JSON",
                    data: {userId: USER_ID},
                    success: function (data) {
                        if (!alert("[" + USER_ID + "] 계정의 모든 권한을 철회하였습니다.")) {
                            window.location.reload();
                        }
                    }
                });
            });
        });
    });

    adminDeleteMemBtnCollect.forEach(adminDeleteMemBtn => {
        adminDeleteMemBtn.addEventListener("click", (evt) => {
            const form = evt.currentTarget.closest("form");
            form.action = "/member/remove";
            form.submit();
        });
    });
}