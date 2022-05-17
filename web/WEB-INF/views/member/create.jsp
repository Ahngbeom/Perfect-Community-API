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
<div class="container-fluid d-flex display-flex-between">
    <div class="h3 w-25">Member Register</div>
    <div class="w-50">
        <form method="post" action="${pageContext.request.contextPath}/member/create" id="MemberRegisterForm">
            <div class="form-group">
                <label>ID</label>
                <input type="text" name="userId" value-status="error" class="form-control" placeholder="User ID"/>
                <p id="member-form-userId-status" class=""></p>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" class="form-control" placeholder="Password">
                <input type="hidden" id="passwordReconfirm" value-status="error" class="form-control"
                       placeholder="Password Reconfirm"/>
                <p id="member-form-password-status" class=""></p>
            </div>
            <div class="form-group">
                <label>Name</label>
                <input type="text" name="userName" class="form-control" value-status="error" placeholder="User Name">
                <p id="member-form-userName-status" class=""></p>
            </div>
            <div class="form-group">
                <label>Authority</label>
                <select name="auth" class="form-control">
                    <option value="ROLE_USER">
                        USER
                    </option>
                    <option value="ROLE_ADMIN">
                        ADMIN
                    </option>
                </select>
            </div>
            <button type="button" class="btn btn-info" id="MemberCreateSubmitBtn">계정 생성</button>
        </form>
    </div>
</div>
<%--<div class="container">--%>
<%--    <div class="d-flex justify-content-center">--%>
<%--        <form>--%>
<%--            <div class="form-group">--%>
<%--                <label for="exampleInputEmail1">Email address</label>--%>
<%--                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"--%>
<%--                       placeholder="Enter email">--%>
<%--                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone--%>
<%--                    else.</small>--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label for="exampleInputPassword1">Password</label>--%>
<%--                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">--%>
<%--            </div>--%>
<%--            <div class="form-check">--%>
<%--                <input type="checkbox" class="form-check-input" id="exampleCheck1">--%>
<%--                <label class="form-check-label" for="exampleCheck1">Check me out</label>--%>
<%--            </div>--%>
<%--            <button type="submit" class="btn btn-primary">Submit</button>--%>
<%--        </form>--%>
<%--        <form method="post" action="${pageContext.request.contextPath}/member/create" id="MemberRegisterForm">--%>
<%--            <table width="1000px">--%>
<%--                <tbody>--%>
<%--                <tr>--%>
<%--                    <th>--%>
<%--                        ID--%>
<%--                    </th>--%>
<%--                    <td>--%>
<%--                        <input type="text" name="userId" value-status="error" class="input-w100"/>--%>
<%--                        <p id="member-form-userId-status" class="" style="font-size: x-small;"></p>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>--%>
<%--                        PW--%>
<%--                    </th>--%>
<%--                    <td>--%>
<%--                        <input type="password" name="password" value-status="error" class="input-w100"/>--%>
<%--                        <input type="hidden" id="passwordReconfirm" value-status="ERROR" class="input-w100"/>--%>
<%--                        <p id="member-form-password-status" class="" style="font-size: x-small;"></p>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>--%>
<%--                        Name--%>
<%--                    </th>--%>
<%--                    <td>--%>
<%--                        <input type="text" name="userName" value-status="error" class="input-w100"/>--%>
<%--                        <p id="member-form-userName-status" class="" style="font-size: x-small;"></p>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th>--%>
<%--                        Authority--%>
<%--                    </th>--%>
<%--                    <td>--%>
<%--                        <select name="auth" class="input-w100">--%>
<%--                            <option value="ROLE_USER">--%>
<%--                                USER--%>
<%--                            </option>--%>
<%--                            <option value="ROLE_ADMIN">--%>
<%--                                ADMIN--%>
<%--                            </option>--%>
<%--                        </select>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--            <div class="display-flex-center" style="margin: 10px">--%>
<%--                <input type="button" value="계정 생성"/>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</div>--%>
</body>
</html>
