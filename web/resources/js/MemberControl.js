function memberControl() {
    const adminDeleteAuthBtnCollect = document.querySelectorAll(".admin-delete-auth-btn");
    const adminDeleteAllAuthBtnCollect = document.querySelectorAll(".admin-deleteAll-auth-btn");
    const adminDeleteMemBtnCollect = document.querySelectorAll(".admin-delete-member-btn");


    adminDeleteAuthBtnCollect.forEach(adminDeleteAuthBtn => {
        adminDeleteAuthBtn.addEventListener('click', evt => {
           console.log(adminDeleteAuthBtn.closest("select"));
        });
    });

    adminDeleteAllAuthBtnCollect.forEach(adminDeleteAllAuthBtn => {
        adminDeleteAllAuthBtn.addEventListener('click', evt => {
            console.log("??");
        });
    });

    adminDeleteMemBtnCollect.forEach(adminDeleteMemBtn => {
        adminDeleteMemBtn.addEventListener("click", evt => {
            let form = adminDeleteMemBtn.closest("form");
            form.action = "/member/remove";
            form.submit();
        });
    });
}