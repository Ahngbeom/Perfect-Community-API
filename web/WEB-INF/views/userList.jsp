<html>
    <body>
        <div class="container-fluid g-2">
            <div class="d-flex gap-2 m-3 p-3">
                <table class="table table-striped" id="userListTable">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nickname</th>
                            <th scope="col">Authorities</th>
                            <th scope="col">Registration Date</th>
                            <th scope="col">Update Date</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
    </body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/user/list.js"></script>
<script type="module">
    import {clearCookie} from "../../resources/js/pageCookie.js";

    clearCookie(POST_FILTER_OPTIONS_COOKIE_NAME);
    clearCookie(POST_DETAILS_COOKIE_NAME);
    clearCookie(PAGINATION_DATA_COOKIE_NAME);

    putUserList();
</script>