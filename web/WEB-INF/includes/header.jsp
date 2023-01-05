<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2023-01-03
  Time: 오후 3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Perfect Community</title>

        <%--    JQuery     --%>
        <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

        <%--    Bootstrap    --%>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
                crossorigin="anonymous"></script>
    </head>
    <body>
        <nav class="navbar fixed-top bg-light px-3">
            <div class="container-fluid">
                    <a href="/" class="navbar-brand text-dark">Perfect Community</a>
                    <div id="api-response-area" class="px-3 bg-warning text-center">
                    </div>
                    <div id="user-state">
                        <%--                    <button type="button" data-user-login-state="false">로그인</button>--%>
                    </div>
            </div>
        </nav>
    </body>
    <script type="application/javascript">
        $(document).ready(() => {
            console.log("Perfect Community API");
            getAuthentication();
            // serverState();
        });
    </script>
</html>
