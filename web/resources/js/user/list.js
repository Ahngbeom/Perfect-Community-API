const userListTable = $("#userListTable");
const userListTbody = userListTable.find("tbody");

function putUserList() {
    $.ajax({
        type: 'get',
        url: '/api/user/list'
    }).done((users) => {
        for (let user of users) {
            console.log(user);
            userListTbody.append("<tr>" +
                "<td>" + user.userId + "</td>" +
                "<td>" + user.nickname + "</td>" +
                "<td>" + user.authorities + "</td>" +
                "<td>" + new Date(user.regDate).toLocaleString() + "</td>" +
                "<td>" + new Date(user.updateDate).toLocaleString() + "</td>" +
                "</tr>")

        }
    })
}