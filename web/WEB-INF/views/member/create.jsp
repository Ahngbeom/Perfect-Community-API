<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2022-05-12
  Time: 오후 8:40
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Member Create</title>
</head>
<body>
<h3>Member Register</h3>
<div class="border-black display-flex-center">
    <form method="post" action="${pageContext.request.contextPath}/member/create" id="MemberRegisterForm">
        <table width="1000px">
            <tbody>
            <tr>
                <th>
                    ID
                </th>
                <td>
                    <input type="text" name="userId" value-status="error" class="input-w100"/>
                    <p id="member-form-userId-status" class="" style="font-size: x-small;"></p>
                </td>
            </tr>
            <tr>
                <th>
                    PW
                </th>
                <td>
                    <input type="password" name="password" value-status="error" class="input-w100"/>
                    <input type="hidden" id="passwordReconfirm" value-status="ERROR" class="input-w100"/>
                    <p id="member-form-password-status" class="" style="font-size: x-small;"></p>
                </td>
            </tr>
            <tr>
                <th>
                    Name
                </th>
                <td>
                    <input type="text" name="userName" value-status="error" class="input-w100"/>
                    <p id="member-form-userName-status" class="" style="font-size: x-small;"></p>
                </td>
            </tr>
            <tr>
                <th>
                    Authority
                </th>
                <td>
                    <select name="auth" class="input-w100">
                        <option value="ROLE_USER">
                            USER
                        </option>
                        <option value="ROLE_ADMIN">
                            ADMIN
                        </option>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="display-flex-center" style="margin: 10px">
            <input type="button" value="계정 생성"/>
        </div>
    </form>
</div>
</body>
</html>
