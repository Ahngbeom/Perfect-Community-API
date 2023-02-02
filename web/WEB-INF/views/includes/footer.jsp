<%--
  ~ Copyright (C) 23. 2. 3. 오전 12:17 Ahngbeom (https://github.com/Ahngbeom)
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~      http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<%--
  Created by IntelliJ IDEA.
  User: bbu0704
  Date: 2023-01-18
  Time: 오후 4:47
  To change this template use File | Settings | File Templates.
--%>
<script src="${pageContext.request.contextPath}/resources/js/pageCookie.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jwt.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/authentication.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/board.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/post.js"></script>
<script type="application/javascript">

    setInterval(() => {
        $("#currentTime").html(new Date());
    }, 1000);

    $(".api-link").on('click', (e) => {
        const api = $(e.target);
        $.ajax({
            type: api.data('api-method'),
            url: api.html(),
            contentType: 'application/json',
            dataType: 'json'
        }).done((data) => {
            $("#api-result").addClass("text-wrap").text(JSON.stringify(data));
        }).fail((xhr) => {
            $("#api-result").html(xhr.responseText);
        });
    })
</script>
